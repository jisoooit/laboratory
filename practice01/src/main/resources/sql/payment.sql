DROP TABLE PAYMENT;

CREATE TABLE PAYMENT (
                         pmId INT AUTO_INCREMENT,
                         uId INT,
                         pId INT,
                         cardName VARCHAR(255),
                         cardNum VARCHAR(255),
                         paymentMethod VARCHAR(255),
                         paymentDate VARCHAR(255),
                         progress VARCHAR(255),
                         productNum VARCHAR(255),
                         savedPoint INT,
                         usePoint INT,
                         PRIMARY KEY (pmId),
                         FOREIGN KEY (uId) REFERENCES MEMBER (uId),
                         FOREIGN KEY (pId) REFERENCES PRODUCT (pId)
);

INSERT INTO PAYMENT (uId, pId, cardName, cardNum, paymentMethod, paymentDate, progress, productNum, savedPoint, usePoint)  VALUES ( 1,1,'p', 'p','p','p','p',6,10,5);
INSERT INTO PAYMENT (uId, pId, cardName, cardNum, paymentMethod, paymentDate, progress, productNum, savedPoint, usePoint)  VALUES ( 1,2,'p2', 'p2','p2','p2','p2',2,100,15);
INSERT INTO PAYMENT (uId, pId, cardName, cardNum, paymentMethod, paymentDate, progress, productNum, savedPoint, usePoint)  VALUES ( 1,3,'p3', 'p3','p3','p3','p3',1,200,10);


SELECT * FROM PAYMENT;
select * from product;

SELECT *
from PAYMENT
 join PRODUCT
on PRODUCT.pId = PAYMENT.pId
join member
on PAYMENT.uId = member.uId
where pmId = 1
