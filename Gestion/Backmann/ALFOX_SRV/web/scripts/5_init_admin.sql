# ---------------------------------------------------------------------
#    crée le user local pour l'accés du serveur à la BD
#    modification du 7 Mars 2017 (v3.0
# ---------------------------------------------------------------------

use alfox;

# drop user sndiscovery@localhost;
create user alfox@10.10.33.155 identified by 'alfox2018';
grant  select,insert,update,delete on alfox.* to alfox@10.10.33.155 identified by 'alfox2018';
