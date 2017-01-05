
INSERT INTO login(
  login_id,
  login_dt_alteracao_senha,
  login_created,
  login_dt_ultimo_login,
  login_email,
  login_nome,
  login_senha,
  login_status) 
VALUES (1
	,current_date
	,current_date
	,current_date
	,'admin'
	,'Admin'
	,'7C4A8D09CA3762AF61E59520943DC26494F8941B'
	,'A'
       );

INSERT INTO ROLE(ROLE_ID, ROLE_NOME) VALUES(1,'ROLE_ADMIN');

INSERT INTO LOGIN_ROLE(USER_ID, ROLE_ID) VALUES(1,1);        