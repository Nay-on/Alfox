# -----------------------------------------------------------------------
#    crée les autorisation du user local pour l'accés du serveur à la BD
# -----------------------------------------------------------------------

use alfox;

# drop user alfox@localhost;
create user alfox@localhost identified by 'alfox31';

grant  select,insert,update,delete on alfox.user to alfox@localhost identified by 'alfox31';
