
INSERT INTO public.scooter_dock(id, available_place, dock_name) VALUES
(1,1,'Centrum');

INSERT INTO public.scooter(id, max_speed, model_name, rental_price, scooter_dock_id, user_account_id) VALUES
(5,25,'ERE-321', 19.11,1,null),
(6,25,'INDIANA R2', 22.12,1,null),
(7,25,'ENERO Formula', 20.12,1,null),
(8,25,'METEOR Hi-Way', 16.11,1,null),
(9,25,'INDIANA X3', 19.55,1,null),
(10,25,'INDIANA R1', 18.59,1,null);

SELECT  setval('public.hibernate_sequence',15,true);
