-- insert default role 'EDITOR'
insert into public.roles (id, name)
values  ('3a98ca86-8841-4405-9f8d-94607fbb7b55', 'EDITOR')
on conflict do nothing;

-- insert default user (username: test, email: test@gmail.com, password: 123)
insert into public.users (id, username, email, password)
values  ('5eab0fb0-e88a-4698-9cd6-910bb5e366bb', 'test', 'test@gmail.com', '$2a$10$Rbvf.SFAxfQSoGSqk/7QIesJECqDzRVUx2Kr2IHIzbHnr5HtFYsFe')
on conflict do nothing;

-- assign role 'EDITOR' to user 'test'
insert into public.users_roles (user_id, role_id)
values  ('5eab0fb0-e88a-4698-9cd6-910bb5e366bb', '3a98ca86-8841-4405-9f8d-94607fbb7b55')
on conflict do nothing;