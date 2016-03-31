Create Database 
        test
 Login to the database
        mysql -h <mysql_hostname> -u <username> -p<password>
 Run the script to populate tables
    USE DATABASE test;
    CREATE TABLE PRODUCTS (PRODUCT_NAME VARCHAR(255) NOT NULL, PRICE INT, LOCATION VARCHAR(30), CURRENCY VARCHAR(5),
    PRIMARY KEY (PRODUCT_NAME));
    
Task 1 is implemented with the assumption that the data is taken from a SQL table

Task 2 is only partially implemented    
    