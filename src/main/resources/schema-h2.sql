CREATE TABLE ROOM(
        ROOM_ID bigint auto_increment PRIMARY KEY,
        NUMBER VARCHAR2(50) NOT NULL,
        CATEGORY_REF bigint,
        PRICE NUMBER(8, 2) NOT NULL
);

CREATE TABLE CATEGORIES
        (
        CATEGORY_ID bigint auto_increment PRIMARY KEY,
        NAME VARCHAR2(50) NOT NULL,
        DESCRIPTION VARCHAR2(250) NOT NULL
        );

CREATE TABLE SERVICE (
        SERVICE_ID bigint auto_increment PRIMARY KEY,
        NAME VARCHAR2(50) NOT NULL,
        DESCRIPTION VARCHAR2(250) NOT NULL,
        PRICE NUMBER(8, 2) NOT NULL
);

CREATE TABLE USER(
    USER_ID bigint auto_increment PRIMARY KEY,
    FIRST_NAME VARCHAR2(50) NOT NULL,
    LAST_NAME VARCHAR2(50) NOT NULL
);

CREATE TABLE ROOM_SERVICE(
        ROOM_REF bigint,
        SERVICE_REF bigint
);

CREATE TABLE ORDERS(
    ORDER_ID bigint auto_increment PRIMARY KEY,
    ROOM_REF bigint,
    USER_REF bigint,
    START_DATE DATE,
    FINISH_DATE DATE
);

ALTER TABLE ROOM
    ADD FOREIGN KEY (CATEGORY_REF)
    REFERENCES CATEGORIES(CATEGORY_ID);

ALTER TABLE ROOM_SERVICE
    ADD FOREIGN KEY (ROOM_REF)
    REFERENCES ROOM(ROOM_ID);

ALTER TABLE ROOM_SERVICE
    ADD FOREIGN KEY (SERVICE_REF)
    REFERENCES SERVICE(SERVICE_ID);

ALTER TABLE ORDERS
    ADD FOREIGN KEY (ROOM_REF)
    REFERENCES ROOM(ROOM_ID);

ALTER TABLE ORDERS
    ADD FOREIGN KEY (USER_REF)
    REFERENCES USER(USER_ID);