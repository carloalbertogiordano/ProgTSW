DROP DATABASE IF EXISTS Ecommerce;
CREATE DATABASE Ecommerce;
USE Ecommerce;

create table Cliente(
Mail varchar(70) PRIMARY KEY,
Pass varchar(50) not null unique,
Nickname varchar(50) not null unique,
Tel varchar(13) not null unique,
Via varchar(100) not null,
Provincia varchar(2) not null,
Administrator boolean not null default false
);

create table Carrello(
	Cod int auto_increment primary key,
    Totale double not null
);

create table Ordine(
ClienteMail varchar(50) not null,
CarrelloCod int not null AUTO_INCREMENT,
Evaso boolean not null, 
foreign key (ClienteMail) references Cliente(Mail),
foreign key (CarrelloCod) references Carrello(Cod),
primary key (ClienteMail, CarrelloCod)
);

create table Pezzo(
	Id int auto_increment primary key,
    Marca varchar (30) not null,
    Modello varchar (30) not null,
    Prezzo double not null,
    Quantita int not null,
    Wattaggio int,
    Tipo varchar (20) not null,
    Frequenza float,
    N_Core int,
    N_Ram int,
    N_USB int,
    N_PCI int,
    MBs int,
    VRAM int,
    N_Watt int,
    W_CPU int,
    FormaMobo int2,
    url varchar (250),
    Descrizione varchar(10000)
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
    N_Carta int not null,
    Intestatario varchar (50) not null,
    CVV int not null,
    Scadenza date not null,
    foreign key (ClienteMail) references Cliente(Mail),
    primary key (ClienteMail, N_Carta)
);

create table Comporre(
	PezzoID int,
    CarrelloCod int,
    Quantita int not null,
	foreign key (PezzoID) references Pezzo(ID),
   	foreign key (CarrelloCod) references Carrello(Cod),
	primary key (PezzoID, CarrelloCod)
);
