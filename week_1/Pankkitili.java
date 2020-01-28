//Pankkitili-olio

class Pankkitili{
    //tilin tiedot ovat yksityisiä, nimi ja tilinro voivat periaatteessa olla myös final
    private String owner;
    private String account;
    private double saldo;

    public Pankkitili(String owner, String account, double saldo){
        //Muodostaja
        this.owner = owner;
        this.account = account;
        this.saldo = saldo;
    }

    // periaatteessa getX() metodit voisi yhdistää, mutta joissain tilanteissa voidaan tarvita vain tiettyä.
    public String getOwner(){
        //Palauttaa omistajan nimen
        return this.owner;
    }
    public String getAccount(){
        //Palauttaa tilinumeron
        return this.account;
    }
    public Double getSaldo(){
        //Palauttaa saldon
        return this.saldo;
    }
    public Boolean checkSaldo(double user_saldo){
        //tarkistaa onko tilillä katetta nostotapahtumaa varten, kutsuu nostometodia jos on.
        if (user_saldo > this.saldo){
            return false;
        } else {
            withdraw(user_saldo);
            return true;
        }
    }
    public void deposit(double sum){
        //talletusmetodi
        this.saldo = this.saldo + sum;
    }
    public void withdraw(double sum){
        //nostometodi
        this.saldo = this.saldo - sum;
    }
}
