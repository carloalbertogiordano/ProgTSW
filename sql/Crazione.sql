DROP DATABASE IF EXISTS Ecommerce;
CREATE DATABASE Ecommerce;
USE Ecommerce;

create table Cliente(
Mail varchar(50) not null unique,
Pass varchar(50) not null unique,
Nickname varchar(50) not null unique,
Tel varchar(12) not null unique,
Via varchar(100) not null,
Provincia varchar(2) not null,
Administrator boolean not null,
primary key (Mail, Pass)
);

create table Carrello(
	Cod int auto_increment primary key,
    Totale int not null
);

create table Ordine(
ClienteMail varchar(50) not null unique,
ClientePassword varchar (50) not null unique,
CarrelloCod int not null unique AUTO_INCREMENT,
Fattura varchar(50) not null unique,
foreign key (ClienteMail, ClientePassword) references Cliente(Mail, Pass),
foreign key (CarrelloCod) references Carrello(Cod),
primary key (ClienteMail, ClientePassword, CarrelloCod, Fattura)
);

create table Pezzo(
	Id int auto_increment primary key,
    Nome varchar (30) not null,
    Marca varchar (30) not null,
    Modello varchar (30) not null,
    Prezzo int not null,
    Wattaggio int not null,
    Tipo varchar (20) not null,
    Frequenza float,
    N_Core int,
    N_Ram int,
    N_USB int,
    N_PCI int,
    MBs int,
    VRAM int,
    N_Watt int,
    Mobo varchar (20),
    W_CPU int
);

create table Sconto(
	DD_Inizio date not null,
    DD_Fine date not null,
    Percentuale int not null,
    primary key (DD_Inizio, DD_Fine)
);

create table Applicare (
	ScontoDD_Inizio date not null,
    ScontoDD_Fine date not null,
    PezzoId int not null,
    foreign key (ScontoDD_Inizio, ScontoDD_Fine) references Sconto(DD_Inizio, DD_Fine),
    foreign key (PezzoId) references Pezzo(Id),
    primary key (ScontoDD_Inizio, ScontoDD_Fine, PezzoId)
);

create table Carta(
	ClienteMail varchar (50) not null,
    ClientePassword varchar (50) not null,
    N_Carta int not null,
    Intestatario varchar (50) not null,
    CVV int not null,
    Scadenza date not null,
    foreign key (ClienteMail) references Cliente(Mail),
    foreign key (ClientePassword) references Cliente(Pass),
    primary key (ClienteMail, ClientePassword, N_Carta)
);

create table Comporre(
	PezzoID int,
    CarrelloCod int,
    Quantità int not null,
    Prezzo int not null,
	foreign key (PezzoID) references Pezzo(ID),
   	foreign key (CarrelloCod) references Carrello(Cod),
	primary key (PezzoID, CarrelloCod)
);


###Popolazione###

#Clienti
insert into Cliente values ("cliente1@placeholdermail.com", "8890faaaf4bf2f913be5ccff9e5f23b88657bd15","Cliente1","+391111111111","Via del cliente 1","CE",false); #Password=Cliente1
insert into Cliente values ("cliente2@placeholdermail.com", "0b2646fdcdc7d726f41cb457877840fa4ef5cd2f","Cliente2","+392222222222","Via del cliente 2","NA",false); #Password=Cliente2
insert into Cliente values ("cliente3@placeholdermail.com", "cd5883c1e209563a8dfca43d005e2f6a87af573d","Cliente3","+393333333333","Via del cliente 3","SA",false); #Password=Cliente3
insert into Cliente values ("admin@placeholdermail.com", "4e7afebcfbae000b22c7c85e5560f89a2a0280b4","Admin","+39000000000","Via degli Admin","GE",true); #Password=Admin

#Carrelli
insert into Carrello values (1, 100);
insert into Carrello (Totale) values (1000);
insert into Carrello (Totale) values (10000);

#Ordini
insert into Ordine values ("cliente1@placeholdermail.com", "8890faaaf4bf2f913be5ccff9e5f23b88657bd15", 1, "1");
insert into Ordine values ("cliente2@placeholdermail.com", "0b2646fdcdc7d726f41cb457877840fa4ef5cd2f", 2, "2");
insert into Ordine values ("cliente3@placeholdermail.com", "cd5883c1e209563a8dfca43d005e2f6a87af573d", 3, "3");

#CPU
insert into Pezzo (Id, Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core) values (1, "cpu1", "Intel", "cpu1a", 200, 300, 4.2, 8);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core) values ("cpu2", "AMD", "cpu2a", 150, 200, 3.2, 6);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core) values ("cpu3", "AMD", "cpu2b", 250, 250, 4.4, 8);

#Mobo
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, N_RAM, N_USB, N_PCI) values ("Mobo1", "Asrock", "Mod1", 300, 30, 2, 6, 3);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, N_RAM, N_USB, N_PCI) values ("Mobo2", "MSI", "Mod2", 600, 30, 8, 8, 5);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, N_RAM, N_USB, N_PCI) values ("Mobo3", "Asus", "Mod3", 300, 30, 2, 6, 3);

#RAM
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza) values ("RAM1", "Samsung", "Mod1", 75, 10, 2666);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza) values ("RAM2", "MSI", "Mod2", 100, 15, 3000);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza) values ("RAM3", "Corsair", "Mod3", 150, 20, 3200);

#HDD
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, MBs) values ("HDD1", "Kingstone", "Mod1hdd", 100, 2, 300);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, MBs) values ("HDD2", "Gigabyte", "Mod2hdd", 150, 2, 400);
#SSD
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, MBs) values ("SSD1", "Corsair", "Mod3ssd", 200, 5, 1300);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, MBs) values ("SSD2", "Kingstone", "Mod4ssd", 300, 5, 2000);

#GPU
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM) values ("GPU1", "NVIDIA", "Mod1", 600, 200, 1200, 6);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM) values ("GPU2", "Gigabyte", "Mod2", 500, 600, 1500, 8);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM) values ("GPU3", "MSI", "Mod3", 800, 600, 2000, 8);

#APU #Riguarda wattaggiosu schema. che casso significa?
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio) values ("Alimentatore1", "Gigabyte", "Mod1", 200, 500);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio) values ("Alimentatore1", "Corsair", "Mod2", 300, 800);
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio) values ("Alimentatore1", "Coolermaster", "Mod3", 400, 1200);

#Case #Mobo e apu?
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio) values ();

#Dissipatore #Riguarda watt
insert into Pezzo (Nome, Marca, Modello, Prezzo, Wattaggio, W_CPU) values ();
