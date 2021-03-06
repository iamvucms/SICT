PGDMP                      
    v            postgres    10.5    10.5 6    L           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            M           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            N           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            O           1262    12938    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE postgres;
             postgres    false            P           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                  postgres    false    2895                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            Q           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    4                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            R           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    2                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                  false            S           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                       false    1            �            1259    16653 
   categories    TABLE     x   CREATE TABLE public.categories (
    cat_id integer NOT NULL,
    cat_name text NOT NULL,
    cat_slug text NOT NULL
);
    DROP TABLE public.categories;
       public         postgres    false    4            �            1259    16689 	   customers    TABLE     �   CREATE TABLE public.customers (
    cus_id integer NOT NULL,
    cus_name text NOT NULL,
    cus_mail text,
    cus_phone text,
    cus_address text,
    cus_country text
);
    DROP TABLE public.customers;
       public         postgres    false    4            �            1259    16680 	   employees    TABLE     �   CREATE TABLE public.employees (
    emp_id integer NOT NULL,
    emp_name text NOT NULL,
    emp_birthday date NOT NULL,
    emp_address text NOT NULL,
    is_working integer DEFAULT 1 NOT NULL
);
    DROP TABLE public.employees;
       public         postgres    false    4            �            1259    16731 
   forget_pwd    TABLE     �   CREATE TABLE public.forget_pwd (
    user_id integer NOT NULL,
    token text NOT NULL,
    expired integer NOT NULL,
    create_at timestamp without time zone NOT NULL
);
    DROP TABLE public.forget_pwd;
       public         postgres    false    4            �            1259    16674    images    TABLE     [   CREATE TABLE public.images (
    product_id integer NOT NULL,
    img_src text NOT NULL
);
    DROP TABLE public.images;
       public         postgres    false    4            �            1259    16713    orderDetails    TABLE     �   CREATE TABLE public."orderDetails" (
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);
 "   DROP TABLE public."orderDetails";
       public         postgres    false    4            �            1259    16697    orders    TABLE       CREATE TABLE public.orders (
    order_id integer NOT NULL,
    emp_id integer NOT NULL,
    cus_id integer NOT NULL,
    ship_id integer NOT NULL,
    receive_address text NOT NULL,
    "totalPay" integer,
    create_at timestamp without time zone NOT NULL
);
    DROP TABLE public.orders;
       public         postgres    false    4            �            1259    16641    products    TABLE     �  CREATE TABLE public.products (
    product_id integer NOT NULL,
    product_name text,
    description text,
    options text NOT NULL,
    price integer,
    cat_id integer NOT NULL,
    sup_id integer NOT NULL,
    status integer DEFAULT 1 NOT NULL,
    slug text NOT NULL,
    view_count bigint DEFAULT '1'::bigint NOT NULL,
    create_at timestamp without time zone NOT NULL,
    update_at timestamp without time zone NOT NULL
);
    DROP TABLE public.products;
       public         postgres    false    4            �            1259    16705    shippers    TABLE     �   CREATE TABLE public.shippers (
    ship_id integer NOT NULL,
    ship_name text NOT NULL,
    ship_mail text NOT NULL,
    ship_phone text NOT NULL
);
    DROP TABLE public.shippers;
       public         postgres    false    4            �            1259    16665 	   suppliers    TABLE     �   CREATE TABLE public.suppliers (
    sup_id integer NOT NULL,
    sup_name text NOT NULL,
    contact_mail text,
    contact_phone text NOT NULL,
    sup_address text,
    sup_country text NOT NULL
);
    DROP TABLE public.suppliers;
       public         postgres    false    4            �            1259    16663    suppliers_sup_id_seq    SEQUENCE     �   CREATE SEQUENCE public.suppliers_sup_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.suppliers_sup_id_seq;
       public       postgres    false    200    4            T           0    0    suppliers_sup_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.suppliers_sup_id_seq OWNED BY public.suppliers.sup_id;
            public       postgres    false    199            �            1259    16723    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    level integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    4            �            1259    16717    votes    TABLE     �   CREATE TABLE public.votes (
    product_id integer NOT NULL,
    cus_id integer NOT NULL,
    comment text NOT NULL,
    stars integer NOT NULL,
    create_at timestamp without time zone NOT NULL,
    vote_id integer NOT NULL
);
    DROP TABLE public.votes;
       public         postgres    false    4            �            1259    16739    votes_vote_id_seq    SEQUENCE     �   CREATE SEQUENCE public.votes_vote_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.votes_vote_id_seq;
       public       postgres    false    207    4            U           0    0    votes_vote_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.votes_vote_id_seq OWNED BY public.votes.vote_id;
            public       postgres    false    210            �
           2604    16668    suppliers sup_id    DEFAULT     t   ALTER TABLE ONLY public.suppliers ALTER COLUMN sup_id SET DEFAULT nextval('public.suppliers_sup_id_seq'::regclass);
 ?   ALTER TABLE public.suppliers ALTER COLUMN sup_id DROP DEFAULT;
       public       postgres    false    199    200    200            �
           2604    16741    votes vote_id    DEFAULT     n   ALTER TABLE ONLY public.votes ALTER COLUMN vote_id SET DEFAULT nextval('public.votes_vote_id_seq'::regclass);
 <   ALTER TABLE public.votes ALTER COLUMN vote_id DROP DEFAULT;
       public       postgres    false    210    207            =          0    16653 
   categories 
   TABLE DATA               @   COPY public.categories (cat_id, cat_name, cat_slug) FROM stdin;
    public       postgres    false    198   9       B          0    16689 	   customers 
   TABLE DATA               d   COPY public.customers (cus_id, cus_name, cus_mail, cus_phone, cus_address, cus_country) FROM stdin;
    public       postgres    false    203   �9       A          0    16680 	   employees 
   TABLE DATA               \   COPY public.employees (emp_id, emp_name, emp_birthday, emp_address, is_working) FROM stdin;
    public       postgres    false    202   :       H          0    16731 
   forget_pwd 
   TABLE DATA               H   COPY public.forget_pwd (user_id, token, expired, create_at) FROM stdin;
    public       postgres    false    209   �:       @          0    16674    images 
   TABLE DATA               5   COPY public.images (product_id, img_src) FROM stdin;
    public       postgres    false    201   ;       E          0    16713    orderDetails 
   TABLE DATA               H   COPY public."orderDetails" (order_id, product_id, quantity) FROM stdin;
    public       postgres    false    206   �;       C          0    16697    orders 
   TABLE DATA               k   COPY public.orders (order_id, emp_id, cus_id, ship_id, receive_address, "totalPay", create_at) FROM stdin;
    public       postgres    false    204   <       <          0    16641    products 
   TABLE DATA               �   COPY public.products (product_id, product_name, description, options, price, cat_id, sup_id, status, slug, view_count, create_at, update_at) FROM stdin;
    public       postgres    false    197   �<       D          0    16705    shippers 
   TABLE DATA               M   COPY public.shippers (ship_id, ship_name, ship_mail, ship_phone) FROM stdin;
    public       postgres    false    205   1>       ?          0    16665 	   suppliers 
   TABLE DATA               l   COPY public.suppliers (sup_id, sup_name, contact_mail, contact_phone, sup_address, sup_country) FROM stdin;
    public       postgres    false    200   �>       G          0    16723    users 
   TABLE DATA               C   COPY public.users (user_id, level, username, password) FROM stdin;
    public       postgres    false    208   #?       F          0    16717    votes 
   TABLE DATA               W   COPY public.votes (product_id, cus_id, comment, stars, create_at, vote_id) FROM stdin;
    public       postgres    false    207   �?       V           0    0    suppliers_sup_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.suppliers_sup_id_seq', 1, false);
            public       postgres    false    199            W           0    0    votes_vote_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.votes_vote_id_seq', 5, true);
            public       postgres    false    210            �
           2606    16662 "   categories categories_cat_slug_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_cat_slug_key UNIQUE (cat_slug);
 L   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_cat_slug_key;
       public         postgres    false    198            �
           2606    16660    categories categories_pk 
   CONSTRAINT     Z   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pk PRIMARY KEY (cat_id);
 B   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_pk;
       public         postgres    false    198            �
           2606    16696    customers customers_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (cus_id);
 @   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pk;
       public         postgres    false    203            �
           2606    16688    employees employees_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pk PRIMARY KEY (emp_id);
 @   ALTER TABLE ONLY public.employees DROP CONSTRAINT employees_pk;
       public         postgres    false    202            �
           2606    16738    forget_pwd forget_pwd_token_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.forget_pwd
    ADD CONSTRAINT forget_pwd_token_key UNIQUE (token);
 I   ALTER TABLE ONLY public.forget_pwd DROP CONSTRAINT forget_pwd_token_key;
       public         postgres    false    209            �
           2606    16704    orders orders_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (order_id);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pk;
       public         postgres    false    204            �
           2606    16650    products products_pk 
   CONSTRAINT     Z   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (product_id);
 >   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pk;
       public         postgres    false    197            �
           2606    16652    products products_slug_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_slug_key UNIQUE (slug);
 D   ALTER TABLE ONLY public.products DROP CONSTRAINT products_slug_key;
       public         postgres    false    197            �
           2606    16712    shippers shippers_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.shippers
    ADD CONSTRAINT shippers_pk PRIMARY KEY (ship_id);
 >   ALTER TABLE ONLY public.shippers DROP CONSTRAINT shippers_pk;
       public         postgres    false    205            �
           2606    16673    suppliers suppliers_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_pk PRIMARY KEY (sup_id);
 @   ALTER TABLE ONLY public.suppliers DROP CONSTRAINT suppliers_pk;
       public         postgres    false    200            �
           2606    16730    users users_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pk;
       public         postgres    false    208            =   U   x�3��M,*Q���K�,��@l.#N�Ă���0�e�鑚�U�dBՙp:'�%r&�).SΐĤ���0����� ��!      B   �   x���9�0E�S� ("[�4 ��fVl%�/pz8@h�&=�а8F��h����1�rM�6k��</�j�\�7��H���z�E� �d��[���S�]�X�Nz���:Em]'mЖ0�3��K�_�{��误�?�Ix      A   i   x�3��H��PpI,�4200�50�5��442VHLJV����4�2�)��KW *̈́��*DSe����R
T��ihii�E��!�_bQiI>�$# B3)F��� ��%�      H   l   x����  ��Vq$��%鿄�{g�YF�L�؜������1�G��&��V�9��Z�^�ux����'D�@��4��B�=8�1o�e��{��"�      @   �   x�u�=�0��9�1%9�uO3�ͪ"�ԂK�C�E�=�s�=�>�ޯ�8��y!6���r�mu!}� .ė~K��A\�/����m�k��O���R�K�"��G1l�k[�rʐ��?mŰYl��S��� ��      E   E   x�=���@��vs-�z����A^�Bf@��3ɉ�G2kE����LOw����O\_�� ��V�      C   �   x�}�AnC!�5>H��6`8K7I7�*5۶���H�"�OcM�D���~��?�I�9	Ï��G���t�u'`1;d��6���yD�n��y��b��Zܗ����Q$���!�J8�,=��hYֳ'�N�­�!$�>j,��)I�٣�T.[]�P�)�Z��y��|��XE�'���[��BD�}h�      <   M  x���Mj�0���)t�X�-ݠ�@7�.
FNE�4��MBB�z��ܢ��"�IJ�pE�HO���f %W�*�n`�P��P��l�ʅgv��Ӽ��U�����5�]ɦ��&Ѳ3
�� � ��X�y��9"Q��DH�i}%,h.�F$
�~;q�J����r���̸���� 7-c|d�Md�K=N�O{�z��&��!�*�]ޙ�G$�"��޶���#Tl�B��e��e��kw�}� �D���.T�`: Ⱥ�X�,.�4��8�(�q3R����cu\����{߬�g"�܏ޜ��^jp�3<�����ʜ�ܠ4�SJ Ve�#      D   z   x�3�.I-KU��O�,��������s9��ML��-��9C2s���9K2s��4E&�~�%
a��y�y@f��E�!�g^iebqF"g&�ᐝ����
Vhianfjbld����� �W0�      ?   X   x�3�t,(�I�,.-(�/*qH����s95��5LL�t�-,9��Ĥd���*��`G.#N�ļ�<�Ă�<��d��`\1z\\\ &�#�      G   ~   x���A
�0��c�V�c�/�ȎLsH	����>���=;R��<�$�)V��x��,;G�ƘC�茳�-`߇Z��ż�([p�Sx���2	}^�uō��,�7Zz���lYW/:���mb�J�GJ�w:.�      F   �   x�m�;�@��>�^ +���d/@�Б�P���YD�i�4PR����e����$'�	� A��4G���L`����[K�i�ɪ˨#h����ߺ�T�*����#�#�Mr�=�)�g��_ng��f ����R3�     