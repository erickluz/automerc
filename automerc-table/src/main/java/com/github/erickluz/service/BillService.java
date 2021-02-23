package com.github.erickluz.service;

import com.github.erickluz.commons.Util;
import com.github.erickluz.domain.Bill;
import com.github.erickluz.domain.BillItems;
import com.github.erickluz.domain.StatusBill;
import com.github.erickluz.dto.BillItemsDTO;
import com.github.erickluz.dto.ProductDTO;
import com.github.erickluz.exception.AuthorizationException;
import com.github.erickluz.exception.DataIntegrityException;
import com.github.erickluz.exception.ObjectNotFoundException;
import com.github.erickluz.repository.BillRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class BillService {

    @Inject
    BillRepository dao;
    @Inject
    ItemsBillService itemsBillService;
    @Inject
    ProductService productService;
    @Inject
    UserService userService;
    @Inject
    Util util;

    public Bill findById(Long id) {
        return dao.findById(id);
    }

    public List<Bill> listAllBillsByUser(Long idUser) {
        return dao.listAllBillsByUser(idUser);
    }

    public List<Bill> listAllBillsClosedByUser(Long idUsuario) {
        return dao.listAllBillsByUser(idUsuario);
    }

    public List<Bill> listAllOpenedBillsByUser(Long idUser) {
        return dao.listAllBillsByUserAndStatus(idUser, StatusBill.OPEN);
    }

    public BillItemsDTO findFullBillById(Long id) throws ObjectNotFoundException {
        Bill bill = dao.findById(id);
        if (bill == null) {
            throw new ObjectNotFoundException("Bill : " + id + " not found ");
        }
        List<BillItems> items = itemsBillService.findItemsByIdBill(bill.getIdBill());
        return new BillItemsDTO(bill, items);
    }

    public Bill salvarComanda(BillItemsDTO bill)
            throws DataIntegrityException, ObjectNotFoundException, AuthorizationException {
        verificarNovaComanda(bill);
        Bill billEntity = new Bill(bill.getTableNumber(), bill.getNameClient(), bill.getAmount());
        billEntity.setOpeningTime(LocalDateTime.now());
        billEntity.setAmount(calculateAmount(bill.getBillItens()));
        billEntity.setIdUser(bill.getIdUser());
        billEntity.setStatus(StatusBill.OPEN);
        dao.saveOrUpdate(billEntity);
        List<BillItems> itensComanda = bill.getBillItens();
        for (BillItems item : itensComanda) {
            item.setBill(billEntity);
        }
        itemsBillService.save(itensComanda);
        return billEntity;
    }

    private void verificarNovaComanda(BillItemsDTO comanda) throws DataIntegrityException {
        if (comanda == null) {
            throw new DataIntegrityException("Invalid Bill");
        }
        if (comanda.getTableNumber() == null || comanda.getTableNumber().compareTo(0) <= 0) {
            throw new DataIntegrityException("Invalid table number");
        }
        if (comanda.getIdUser() == null) {
            throw new DataIntegrityException("Invalid User");
        }
        if (findBillByTableNumber(comanda.getTableNumber(), comanda.getIdUser(), StatusBill.OPEN) != null) {
            throw new DataIntegrityException("Table number in use");
        }
    }

    private Bill findBillByTableNumber(Integer tableNumber, Long idUser, StatusBill statusComanda) {
        return dao.findBillByTableNumber(tableNumber, idUser, statusComanda);
    }

    public void editBill(BillItemsDTO bill) throws DataIntegrityException, ObjectNotFoundException {
        verifyBill(bill);
        Bill billEntity = dao.findById(bill.getIdBill());
        if (billEntity == null) {
            throw new ObjectNotFoundException("Bill not found");
        }
        List<BillItems> billItems = new ArrayList<>();
        for (BillItems item : bill.getBillItens()) {
            BillItems itemEntity = itemsBillService.findItemByProductAndBill(item.getIdProduct(),
                    billEntity.getIdBill());
            if (itemEntity == null) {
                itemEntity = item;
            } else {
                itemEntity.setQuantity(item.getQuantity());
            }
            itemEntity.setBill(billEntity);
            billItems.add(itemEntity);
        }
        billEntity.setNameClient(bill.getNameClient());
        billEntity.setAmount(calculateAmount(bill.getBillItens()));
        dao.persist(billEntity);
        itemsBillService.deleteItemsByBill(billEntity.getIdBill());
        itemsBillService.save(billItems);
    }

    private BigDecimal calculateAmount(List<BillItems> items) throws ObjectNotFoundException {
        List<Long> idProducts = items.stream().map(item -> item.getIdProduct()).collect(Collectors.toList());
        List<ProductDTO> products = productService.listByIds(idProducts);
        BigDecimal amount = BigDecimal.ZERO;
        for (ProductDTO product : products) {
            BigDecimal quantity = getQuantityProduct(items, product.getIdProduct());
            amount = amount.add(product.getValue().multiply(quantity));
        }
        return amount;
    }

    private BigDecimal getQuantityProduct(List<BillItems> items, Long idProduct) {
        Integer qtd = items.stream()
                .filter(p -> p.getIdProduct() == idProduct)
                .map(BillItems::getQuantity)
				.findFirst()
                .orElse(null);
        return BigDecimal.valueOf(qtd);
    }

    private void verifyBill(BillItemsDTO bill) throws DataIntegrityException {
        if (bill == null) {
            throw new DataIntegrityException("Empty bill");
        }
        for (BillItems billItem : bill.getBillItens()) {
            if (billItem.getQuantity() == null || billItem.getQuantity().compareTo((short) 0) < 0) {
                throw new DataIntegrityException("Invalid quantity number");
            }
        }
    }

    public void deleteBill(Long idBill) {
        itemsBillService.deleteItemsByBill(idBill);
        dao.deleteById(idBill);
    }

    /**
     * @param bill
     * @throws DataIntegrityException
     * @throws ObjectNotFoundException
     */
    public void AddAnItemInABill(BillItemsDTO bill)
            throws DataIntegrityException, ObjectNotFoundException {
        verifyBill(bill);
        Bill billEntity = dao.findFullBillById(bill.getIdBill());
        if (billEntity == null) {
            throw new ObjectNotFoundException("Bill not found");
        }
        BigDecimal amount = BigDecimal.ZERO;
        for (BillItems item : bill.getBillItens()) {
            boolean isItemInBill = false;
            List<BillItems> itemsFromBill = itemsBillService.findItemsByIdBill(billEntity.getIdBill());
            BillItems entityBillItem;
            for (BillItems entityItem : itemsFromBill) {
                if (entityItem.getIdProduct().equals(item.getIdProduct())) {
                    entityItem.setQuantity(entityItem.getQuantity() + item.getQuantity());
                    entityBillItem = itemsBillService.saveOrUpdate(entityItem);
                    amount = amount.add(entityBillItem.getIdProduct().getPreco() //update the amount from the bill
                            .multiply(BigDecimal.valueOf(entityBillItem.getQuantidade().doubleValue())));
                    isItemInBill = true;
                    break;
                }
            }
            if (!isItemInBill) {
                item.setBill(billEntity);
                itemsBillService.saveOrUpdate(item);
                ProductDTO product = productService.findById(item.getIdProduct());
                amount = amount
                        .add(product.getPreco().multiply(BigDecimal.valueOf(item.getQuantity().doubleValue())));
            }
        }
        billEntity.setAmount(amount);
        dao.saveOrUpdate(billEntity);
    }

    public void closeBill(Long idBill) throws DataIntegrityException, ObjectNotFoundException {
        if (idBill == null) {
            throw new DataIntegrityException("Invalid bill");
        }
        Bill comanda = dao.findById(idBill);
        comanda.setStatus(StatusBill.CLOSED);
        comanda.setClosingTime(LocalDateTime.now());
        dao.persist(comanda);
    }
}