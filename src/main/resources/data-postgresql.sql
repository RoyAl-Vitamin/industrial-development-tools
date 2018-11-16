INSERT INTO public."user"(user_id, user_full_name) VALUES (0, 'Ivan Ivanov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (1, 'Ivan Petrov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (2, 'Ivan Fedorov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (3, 'Ivan Mihalovich');

INSERT INTO public."user"(user_id, user_full_name) VALUES (4, 'Petr Ivanov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (5, 'Petr Petrov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (6, 'Petr Fedorov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (7, 'Petr Mihalovich');

INSERT INTO public."user"(user_id, user_full_name) VALUES (8, 'Fedor Ivanov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (9, 'Fedor Petrov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (10, 'Fedor Fedorov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (11, 'Fedor Mihalovich');

INSERT INTO public."user"(user_id, user_full_name) VALUES (12, 'Misha Ivanov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (13, 'Misha Petrov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (14, 'Misha Fedorov');

INSERT INTO public."user"(user_id, user_full_name) VALUES (15, 'Misha Mihalovich');


INSERT INTO public."group_product"(group_product_id, group_product_name) VALUES (0, 'LEKARSTVA');

INSERT INTO public."group_product"(group_product_id, group_product_name) VALUES (1, 'ALCOGOL');

INSERT INTO public."group_product"(group_product_id, group_product_name) VALUES (2, 'TABAK');

INSERT INTO public."group_product"(group_product_id, group_product_name) VALUES (3, 'IGRY');

INSERT INTO public."group_product"(group_product_id, group_product_name) VALUES (4, 'ODEJDA');


INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (0, 'ASPIRIN', 0, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (1, 'NASHATYR', 0, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (2, 'WISKEY', 1, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (3, 'VODKA', 1, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (4, 'SIGARETTE', 2, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (5, 'SIGARY', 2, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (6, 'GTA', 3, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (7, 'RDR', 3, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (8, 'PUMA', 4, null);

INSERT INTO public.product(product_id, product_name, group_product_id, order_position_id) VALUES (9, 'NIKE', 4, null);
