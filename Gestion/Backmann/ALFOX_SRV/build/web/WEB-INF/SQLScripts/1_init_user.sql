# -----------------------------------------------------------------------------
#             init des tables de la BD alfox
# -----------------------------------------------------------------------------
# Mots de passe cryptés des users :
#   636D61CF9094A62A81836F3737D9C0DA  pwd : responsable
#   57CB773AE7A82C8C8AAE12FA8F8D7ABD  pwd : maintenance
# -----------------------------------------------------------------------------

use alfox;

insert into `user` (`Role`, `Mdp`, `Mail`) values 
    ('responsable','636D61CF9094A62A81836F3737D9C0DA','responsable@gmail.com'),
    ('maintenance','57CB773AE7A82C8C8AAE12FA8F8D7ABD','maintenance@gmail.com');
