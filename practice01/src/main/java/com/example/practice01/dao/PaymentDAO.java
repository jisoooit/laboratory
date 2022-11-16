package com.example.practice01.dao;

import com.example.practice01.database.JDBCMgr;
import com.example.practice01.dto.ShopListDTO;
import com.example.practice01.dto.ShopListDetailDTO;
import com.example.practice01.entity.Payment;
import com.example.practice01.entity.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PaymentDAO {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;


    private static final String PAYMENT_SELECT = "select * from payment where pmId = ?";
    private static final String PAYMENT_DELETE = "delete from payment where pmId = ?";
    private static final String PRODUCT_SELECT = "select * from product where pId = ?";
    private static final String SHOPLIST_DETAIL ="SELECT *\n" +
                                                                                        "from payment\n" +
                                                                                        "join product\n" +
                                                                                        "on product.pId = payment.pId\n" +
                                                                                        "where pmId = ?";

    private static final String SHOPLIST_SELECT_ALL ="SELECT *\n" +
                                                                                                "from payment\n" +
                                                                                                "join product\n" +
                                                                                                "on product.pId = payment.pId\n" +
                                                                                                "where uId = ?";


    public Payment paymentSelect(int pmId) {
        Payment payment = null;
        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(PAYMENT_SELECT);
            stmt.setInt(1,pmId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int uId = rs.getInt("uId");
                int pId = rs.getInt("payment.pId");
                String cardName = rs.getString("cardName");
                String cardNum = rs.getString("cardNum");
                String paymentMethod = rs.getString("paymentMethod");
                String paymentDate = rs.getString("paymentDate");
                String progress = rs.getString("progress");
                int productNum = rs.getInt("productNum");
                int savedPoint = rs.getInt("savedPoint");
                int usePoint = rs.getInt("usePoint");


                payment =  new Payment(pmId, uId, pId, cardName
                        , cardNum, paymentMethod,paymentDate,progress
                        ,productNum,savedPoint,usePoint);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs, stmt, conn);
        }
        return payment;
    }

    public Product productSelect(int pId) {
        Product product = null;
        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(PRODUCT_SELECT);
            stmt.setInt(1,pId);

            rs = stmt.executeQuery();
            if (rs.next()) {

                String pName = rs.getString("pName");
                int pPrice = rs.getInt("pPrice");
                String pCompany = rs.getString("pCompany");
                String pCompanyPhone = rs.getString("pCompanyPhone");

                product =  new Product(pId, pName, pPrice, pCompany, pCompanyPhone);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs, stmt, conn);
        }
        return product;
    }

    public int delete(int pmId) {
        int res = 0;
        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(PAYMENT_DELETE);
            stmt.setInt(1, pmId);
            res = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(stmt, conn);
        }
        return res;
    }

    public ShopListDetailDTO shopListDetail(int pmId){
        ShopListDetailDTO shopListDetailDTO = new ShopListDetailDTO();

        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(SHOPLIST_DETAIL);
            stmt.setInt(1,pmId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int uId = rs.getInt("uId");
                int pId = rs.getInt("payment.pId");
                String cardName = rs.getString("cardName");
                String cardNum = rs.getString("cardNum");
                String paymentMethod = rs.getString("paymentMethod");
                String paymentDate = rs.getString("paymentDate");
                String progress = rs.getString("progress");
                int productNum = rs.getInt("productNum");
                int savedPoint = rs.getInt("savedPoint");
                int usePoint = rs.getInt("usePoint");

                String pName = rs.getString("pName");
                int pPrice = rs.getInt("pPrice");
                String pCompany = rs.getString("pCompany");
                String pCompanyPhone = rs.getString("pCompanyPhone");


                shopListDetailDTO =new  ShopListDetailDTO(pmId, uId, pId, cardName
                        , cardNum, paymentMethod,paymentDate,progress
                        ,productNum,savedPoint,usePoint, pName, pPrice, pCompany, pCompanyPhone);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs, stmt, conn);
        }
        return shopListDetailDTO;
    }

    public List<Payment> userPaymentAll(int uId) {
        List<Payment> paymentList = new LinkedList<>();

        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(SHOPLIST_SELECT_ALL);
            stmt.setInt(1,uId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int pmId = rs.getInt("pmId");
                int pId = rs.getInt("payment.pId");
                String cardName = rs.getString("cardName");
                String cardNum = rs.getString("cardNum");
                String paymentMethod = rs.getString("paymentMethod");
                String paymentDate = rs.getString("paymentDate");
                String progress = rs.getString("progress");
                int productNum = rs.getInt("productNum");
                int savedPoint = rs.getInt("savedPoint");
                int usePoint = rs.getInt("usePoint");

                String pName = rs.getString("pName");
                int pPrice = rs.getInt("pPrice");
                String pCompany = rs.getString("pCompany");
                String pCompanyPhone = rs.getString("pCompanyPhone");


                paymentList.add(new Payment(pmId, uId, pId, cardName
                        , cardNum, paymentMethod,paymentDate,progress
                        ,productNum,savedPoint,usePoint));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs, stmt, conn);
        }
        return paymentList;
    }

    public List<ShopListDTO> userPaymentAll2(int uId) {
        List<ShopListDTO> shopListDTOs = new LinkedList<>();

        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(SHOPLIST_SELECT_ALL);
            stmt.setInt(1,uId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int pmId = rs.getInt("pmId");
                int pId = rs.getInt("payment.pId");

                String paymentMethod = rs.getString("paymentMethod");
                String paymentDate = rs.getString("paymentDate");
                String progress = rs.getString("progress");
                int productNum = rs.getInt("productNum");

                String pName = rs.getString("pName");
                int pPrice = rs.getInt("pPrice");
                String pCompany = rs.getString("pCompany");
                String pCompanyPhone = rs.getString("pCompanyPhone");


                shopListDTOs.add(new ShopListDTO(pmId, uId, pId,
                        paymentMethod,paymentDate,progress
                        ,productNum,pName,pPrice,pCompany,pCompanyPhone));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs, stmt, conn);
        }
        return shopListDTOs;
    }
}
