DROP DATABASE IF EXISTS Ecommerce;
CREATE DATABASE Ecommerce;
USE Ecommerce;

create table Cliente(
Mail varchar(50) not null unique,
Pass varchar(50) not null unique, 
Nickname varchar(50) not null unique, 
Tel varchar(12) not null unique, 
Via varchar(100) not null, 
Privincia varchar(2) not null, 
Administrator boolean not null, 
primary key (Mail, Pass) 
);

create table Carrello(
	Cod int primary key,
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
	Id int primary key,
    Nome varchar (30) not null,
    Marca varchar (30) not null,
    Modello varchar (30) not null,
    Prezzo int not null,
    Wattaggio int not null,
    Tipo varchar (20) not null,
    Frequenza int,
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



