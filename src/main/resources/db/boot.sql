INSERT INTO public.pp_role
(id,creation_date, last_updated, "name")
VALUES(100000,now(),now(), 'EMPLOYEE');

INSERT INTO public.pp_role
(id,creation_date, last_updated, "name")
VALUES(100001,now(),now(), 'ADMIN');


INSERT INTO public.pp_user
(id, active, creation_date, last_updated, created_by_id, mail, "name", user_type)
VALUES(1, true, now(), now(), NULL, 'admin', 'Administrator', 'ADMIN');

INSERT INTO public.pp_credential
(id, active, creation_date, last_updated, created_by_id, mail, "password", user_id)
VALUES(1, true, now(), now(), NULL, 'admin', 
'{bcrypt}$2a$10$ZxnXDNc2UkMCkMGYyHBAKuroXkOCY5JcvDNB1LrWvou4Lwsgu/5QG', 1);

INSERT INTO public.pp_user_role
(user_id, role_id)
VALUES(1, (select id from pp_role pr where name ='ADMIN'));