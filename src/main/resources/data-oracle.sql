
Insert into AUTH_USER
   (ID, VERSION, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CELL_NO,
    COUNTRY, CREDENTIALS_NON_EXPIRED, CURRENCY, DISPLAY_NAME, DOB,
    EMAIL, ENABLED, FULL_NAME, GENDER, LANG,
    LOCALE, MENU_ORIENTATION, OPEN_IN_NEW_PAGE, PASSWORD, PIC,
    TITLE, USERNAME)
 Values
   (1, 0, 1, 1, '01912917834',
    'IN', 1, 'BDT', 'Manik', TO_DATE('05/07/1983 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
    'manikmonir@gmail.com', 1, 'MOHAMMAD BADIUZZAMAN', 'MALE', 'bn',
    'bn_IN', 'MENU_LEFT', 1, '$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC', 'aaaa.jpg',
    'MR', 'mac');
Insert into AUTH_USER
   (ID, VERSION, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CELL_NO,
    COUNTRY, CREDENTIALS_NON_EXPIRED, CURRENCY, DISPLAY_NAME, DOB,
    EMAIL, ENABLED, FULL_NAME, GENDER, LANG,
    LOCALE, MENU_ORIENTATION, OPEN_IN_NEW_PAGE, PASSWORD, PIC,
    TITLE, USERNAME)
 Values
   (2, 0, 1, 1, '01912917832',
    'BD', 1, 'BDT', 'Saif', TO_DATE('11/23/1989 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
    'saif_hmk@live.com', 1, 'MD. HOSHEN MAHMUD KHAN', 'MALE', 'bn',
    'bn_BD', 'MENU_LEFT', 1, '$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC', 'bbbb.jpg',
    'MR', 'saif_hmk');
Insert into AUTH_USER
   (ID, VERSION, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CELL_NO,
    COUNTRY, CREDENTIALS_NON_EXPIRED, CURRENCY, DISPLAY_NAME, DOB,
    EMAIL, ENABLED, FULL_NAME, GENDER, LANG,
    LOCALE, MENU_ORIENTATION, OPEN_IN_NEW_PAGE, PASSWORD, PIC,
    TITLE, USERNAME)
 Values
   (3, 0, 1, 1, '01912917855',
    'BD', 1, 'BDT', 'Anis', TO_DATE('09/17/1980 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
    'anis.object@gmail.com', 1, 'MOHAMMAD ANISUR RAHMAN KHAN', 'MALE', 'bn',
    'bn_BD', 'MENU_LEFT', 1, '$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC', 'cccc.jpg',
    'MR', 'anis');
Insert into AUTH_USER
   (ID, VERSION, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CELL_NO,
    COUNTRY, CREDENTIALS_NON_EXPIRED, CURRENCY, DISPLAY_NAME, DOB,
    EMAIL, ENABLED, FULL_NAME, GENDER, LANG,
    LOCALE, MENU_ORIENTATION, OPEN_IN_NEW_PAGE, PASSWORD, PIC,
    TITLE, USERNAME)
 Values
   (4, 0, 1, 1, '01912917839',
    'BD', 1, 'BDT', 'RAJIB', TO_DATE('12/01/1991 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
    'rajib@gmail.com', 1, 'Md. Rajib Hossain Hossain', 'MALE', 'bn',
    'bn_BD', 'MENU_LEFT', 1, '$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC', 'dddd.jpg',
    'MR', 'rajib');
Insert into AUTH_USER
   (ID, VERSION, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CELL_NO,
    COUNTRY, CREDENTIALS_NON_EXPIRED, CURRENCY, DISPLAY_NAME, DOB,
    EMAIL, ENABLED, FULL_NAME, GENDER, LANG,
    LOCALE, MENU_ORIENTATION, OPEN_IN_NEW_PAGE, PASSWORD, PIC,
    TITLE, USERNAME)
 Values
   (5, 0, 1, 1, '01912945344',
    'BD', 1, 'BDT', 'Rowshon', TO_DATE('12/01/1991 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
    'rowshon@gmail.com', 1, 'Rowshon Chowdhoury', 'FEMALE', 'bn',
    'bn_BD', 'MENU_LEFT', 1, '$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC', 'eeee.jpg',
    'MS', 'rowshon');

----------------------------------

Insert into AUTH_ROLE
   (ID, VERSION, AUTHORITY, IS_ACTIVE)
 Values
   (1, 0, 'ADMIN', 1);
Insert into AUTH_ROLE
   (ID, VERSION, AUTHORITY, IS_ACTIVE)
 Values
   (2, 0, 'USER', 1);

-----------------------------------

Insert into AUTH_REQUEST_MAP
   (ID, VERSION, CONFIG_ATTRIBUTE, URL)
 Values
   (1, 0, '*', '/resources/**,/,/index,/login,/about,/signup');
Insert into AUTH_REQUEST_MAP
   (ID, VERSION, CONFIG_ATTRIBUTE, URL)
 Values
   (2, 0, 'USER', '/**');

------------------------------------------

Insert into AUTH_USER_AUTH_ROLE
   (AUTH_USER_ID, AUTH_ROLE_ID)
 Values
   (1, 1);
Insert into AUTH_USER_AUTH_ROLE
   (AUTH_USER_ID, AUTH_ROLE_ID)
 Values
   (1, 2);
Insert into AUTH_USER_AUTH_ROLE
   (AUTH_USER_ID, AUTH_ROLE_ID)
 Values
   (5, 2);

------------------------------------

Insert into POS_SUPPLIER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 SUPPLIER_GROUP, MOBILE)
Values
  (2000, '001', 'SUPPLIER', 'Akhtar Group', 'আখতার গ্রুপ',
   'GRADE_B', '425235');
Insert into POS_SUPPLIER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 SUPPLIER_GROUP, MOBILE)
Values
  (2001, '002', 'SUPPLIER', 'Star Board Mills Limited', 'স্টার বোর্ড মিলস লিমিটেড',
   'GRADE_C', '5466');
Insert into POS_SUPPLIER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 SUPPLIER_GROUP, MOBILE)
Values
  (2002, '003', 'SUPPLIER', 'Super Board Mills Limited', 'সুপার বোর্ড মিলস লিমিটেড',
   'GRADE_C', '4654');
Insert into POS_SUPPLIER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 SUPPLIER_GROUP, MOBILE)
Values
  (2003, '004', 'SUPPLIER', 'Akij Board Mills Ltd', 'আকিজ বোর্ড মিলস লিমিটেড',
   'GRADE_A', '7567');
Insert into POS_SUPPLIER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 SUPPLIER_GROUP, MOBILE)
Values
  (2004, '005', 'SUPPLIER', 'Amber Board', 'অ্যাম্বার বোর্ড',
   'GRADE_A', '757778');

----------------------------------------

Insert into POS_CUSTOMER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 ADDRESS, CUSTOMER_GROUP, MOBILE)
Values
  (30000, '00000', 'CUSTOMER', 'Flying Customer', 'উরন্ত ক্রেতা',
   'world', 'GRADE_B', '01912917834');
Insert into POS_CUSTOMER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 ADDRESS, CUSTOMER_GROUP, MOBILE)
Values
  (30001, '00002', 'CUSTOMER', 'saif', 'সাইফ',
   'shantinagar', 'GRADE_B', '2222');
Insert into POS_CUSTOMER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 ADDRESS, CUSTOMER_GROUP, MOBILE)
Values
  (30002, '00003', 'CUSTOMER', 'anis', 'আনিস',
   'mugda', 'GRADE_A', '01912917833');
Insert into POS_CUSTOMER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 VERSION, ADDRESS, CUSTOMER_GROUP, MOBILE)
Values
  (30003, '00004', 'CUSTOMER', 'debasis', 'দেবাশিস',
   0, 'dhanmondi', 'GRADE_B', '7777');
Insert into POS_CUSTOMER
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE,
 VERSION, ADDRESS, CUSTOMER_GROUP, MOBILE)
Values
  (30004, '00001', 'CUSTOMER', 'manik', 'মানিক',
   0, '112 jn saha road', 'GRADE_B', '57576');

-------------------------------------

Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (432432, '001', 'TYPE', 'Vermatic', 'বার্মাটিক');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (5435, '005', 'ITEM', 'Plywood', 'প্লাইউড');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (432, '001', 'ITEM', 'Veneer', 'ভ্যিনিয়র');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (7567, '003', 'TYPE', 'Champa', 'চাম্পা');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (5455, '006', 'ITEM', 'Jutex Board', 'জুটস বোর্ড');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (4664, '007', 'ITEM', 'Woodex Board', 'উডস বোর্ড');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (65, '002', 'MEASURE', '18 mm', '১৮ মিমি');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (54654, '003', 'MEASURE', '12 mm', '১২ মিমি');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (546, '001', 'MEASURE', '09 mm', '০৯ মিমি');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (54545, '001', 'COLOR', 'Red', 'লাল');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (6546546, '007', 'TYPE', 'Garjon', 'গর্জন');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (61, '006', 'TYPE', 'Comercial', 'কমার্শিয়াল');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (60, '005', 'TYPE', 'Crowntic', 'ক্রাউনটিক');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (59, '004', 'TYPE', 'Supertic', 'সুপারটিক');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (57, '002', 'TYPE', 'Merine', 'মেরিন');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (54, '004', 'ITEM', 'PVC', 'পিভিসি');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (53, '003', 'ITEM', 'Partex', 'পারটেক্স');
Insert into POS_PRODUCT_AC
(ID, CODE, AC_TYPE, FULL_NAME, FULL_NAME_NATIVE)
Values
  (52, '002', 'ITEM', 'Melamine', 'মেলামাইন');

--------------------------------------

Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_ONE, AC_THREE)
Values
  (131, '111', ' Vermatic  Star Board Mills Limited', ' বার্মাটিক  স্টার বোর্ড মিলস লিমিটেড', 18,
   40, 20, 22, 2001, 432432);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (134, '222', '18 mm Garjon Plywood Akhtar Group', '১৮ মিমি গর্জন প্লাইউড আখতার গ্রুপ', 9,
   30, 10, 12, 5435, 2000,
   6546546, 65);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_ONE, AC_THREE)
Values
  (323, '333', ' Garjon  Star Board Mills Limited', ' গর্জন  স্টার বোর্ড মিলস লিমিটেড', 30,
   50, 30, 33, 2001, 6546546);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (434, '444', '18 mm Vermatic Melamine Star Board Mills Limited', '১৮ মিমি বার্মাটিক মেলামাইন স্টার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2001,
   432432, 65);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (133, '133', '12 mm Garjon Plywood Akhtar Group', '১২ মিমি গর্জন প্লাইউড আখতার গ্রুপ', 9,
   30, 10, 12, 5435, 2000,
   6546546, 54654);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (132, '132', '09 mm Garjon Plywood Akhtar Group', '০৯ মিমি গর্জন প্লাইউড আখতার গ্রুপ', 9,
   30, 10, 12, 5435, 2000,
   6546546, 546);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (130, '130', '12 mm Vermatic Melamine Star Board Mills Limited', '১২ মিমি বার্মাটিক মেলামাইন স্টার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2001,
   432432, 54654);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (129, '129', '09 mm Vermatic Melamine Star Board Mills Limited', '০৯ মিমি বার্মাটিক মেলামাইন স্টার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2001,
   432432, 546);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (128, '128', '18 mm Vermatic Melamine Super Board Mills Limited', '১৮ মিমি বার্মাটিক মেলামাইন সুপার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2002,
   432432, 65);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (127, '127', '12 mm Vermatic Melamine Super Board Mills Limited', '১২ মিমি বার্মাটিক মেলামাইন সুপার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2002,
   432432, 54654);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (126, '126', '09 mm Vermatic Melamine Super Board Mills Limited', '০৯ মিমি বার্মাটিক মেলামাইন সুপার বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2002,
   432432, 546);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (125, '125', '18 mm Vermatic Melamine Akij Board Mills Ltd', '১৮ মিমি বার্মাটিক মেলামাইন আকিজ বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2003,
   432432, 65);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (124, '124', '12 mm Vermatic Melamine Akij Board Mills Ltd', '১২ মিমি বার্মাটিক মেলামাইন আকিজ বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2003,
   432432, 54654);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (123, '123', '09 mm Vermatic Melamine Akij Board Mills Ltd', '০৯ মিমি বার্মাটিক মেলামাইন আকিজ বোর্ড মিলস লিমিটেড', 21,
   40, 20, 23, 52, 2003,
   432432, 546);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (122, '122', '18 mm Vermatic Melamine Amber Board', '১৮ মিমি বার্মাটিক মেলামাইন অ্যাম্বার বোর্ড', 21,
   40, 20, 23, 52, 2004,
   432432, 65);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (121, '121', '12 mm Vermatic Melamine Amber Board', '১২ মিমি বার্মাটিক মেলামাইন অ্যাম্বার বোর্ড', 21,
   40, 20, 23, 52, 2004,
   432432, 54654);
Insert into POS_PRODUCT
(ID, CODE, FULL_NAME, FULL_NAME_NATIVE, UNIT_PRICE_PURCHASE_STD,
 UNIT_PRICE_SALES_MAX, UNIT_PRICE_SALES_MIN, UNIT_PRICE_SALES_STD, AC_FOUR, AC_ONE,
 AC_THREE, AC_TWO)
Values
  (120, '120', '09 mm Vermatic Melamine Amber Board', '০৯ মিমি বার্মাটিক মেলামাইন অ্যাম্বার বোর্ড', 21,
   40, 20, 23, 52, 2004,
   432432, 546);

---------------------------------

Insert into ADM_MODULE
(ID, CODE, FULL_NAME, IS_ACTIVE)
Values
  (700, '01', 'Inventory', 1);
Insert into ADM_MODULE
(ID, CODE, FULL_NAME, IS_ACTIVE)
Values
  (701, '02', 'Account', 1);

-----------------------------------

Insert into ADM_PROCESS
(ID, VERSION, CODE, FULL_NAME, IS_ACTIVE,
 CMD, ADM_MODULE_ID)
Values
  (900, 0, '001', 'Inventory Close', 1,
   'aa', 700);

-----------------------------------

Insert into ADM_REPORT
(ID, CODE, FULL_NAME, IS_ACTIVE, FILE_NAME,
 ADM_MODULE_ID)
Values
  (800, '001', 'Stock', 1, 'pos_inv_stock.jasper',
   700);


