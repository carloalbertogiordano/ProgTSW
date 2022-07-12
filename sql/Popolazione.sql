USE Ecommerce;

#Clienti
insert into Cliente values ("cliente1@placeholdermail.com", "1e0fe6e208b6be55e3abcbeb137bb4024ff0f0409beaaeda926aeed7340a7fb997ed506114ee8529e1215b7be621368f10720fbe5ac4f1b8822a9b2edb3c4e80","Cliente1","+391111111111","Via del cliente 1","CE", "Comune 1", 12345, false); #Password=Cliente1
insert into Cliente values ("cliente2@placeholdermail.com", "50f85a2051c767477c2133d65070d42a3d0ebc6eb2b1181f39d9e207e4b0e68fc9b4d0f1b7588b24c098bda3b5f00adb96a5bae8140f4103cddc6875453b89a4","Cliente2","+392222222222","Via del cliente 2","NA", "Comune 2", 56789, false); #Password=Cliente2
insert into Cliente values ("cliente3@placeholdermail.com", "24dbe144d49e0f95d42e57ad1f1747e498dfea0443e47366e4aea53da7ec2ea196fd4b865c34ba09b8143542913bb2d7140f8fe3b59e496649e07eb9e96fea8f","Cliente3","+393333333333","Via del cliente 3","SA", "Comune 3", 78910, false); #Password=Cliente3
insert into Cliente values ("admin@placeholdermail.com", "887375daec62a9f02d32a63c9e14c7641a9a8a42e4fa8f6590eb928d9744b57bb5057a1d227e4d40ef911ac030590bbce2bfdb78103ff0b79094cee8425601f5","Admin","+39000000000","Via degli Admin","GE", "Comune Admin", 11111, true); #Password=Admin

#Carrelli
insert into Carrello values (1, 100);
insert into Carrello (Totale) values (1000);
insert into Carrello (Totale) values (10000);
insert into Carrello (Totale) values (200);

#Ordini
insert into Ordine values ("cliente1@placeholdermail.com", 1, 1, "Via del cliente 1","CE", "Comune 1", 12345);
insert into Ordine values ("cliente1@placeholdermail.com", 4, 1, "Via del cliente 1","CE", "Comune 1", 12345);
insert into Ordine values ("cliente2@placeholdermail.com", 2, 1, "Via del cliente 2","NA", "Comune 2", 56789);
insert into Ordine values ("cliente3@placeholdermail.com", 3, 1, "Via del cliente 3","SA", "Comune 3", 78910);

#CPU
insert into Pezzo (Id, tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, N_Core, url) values (1, "CPU", "Intel", "i7", 200, 50, 300, 4.2, 8, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, N_Core, url) values ("CPU", "AMD", "Ryzen7", 150, 200, 100, 3.2, 6, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, N_Core, url) values ("CPU", "AMD", "Ryzen7", 250, 250, 20, 4.4, 8, "Images/placeHolder");

#Mobo #matx=0, atx=1, eatx=2
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "Asrock", "Z590 OC Formula", 300, 40, 2, 6, 3, 1, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "MSI", "MPG Z690", 600, 300, 8, 8, 5, 2, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "Asus", "ROG Crossair VIII Impact", 300, 180, 2, 6, 3, 0, "Images/placeHolder");

#RAM
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Frequenza, url) values ("RAM", "Corsair", "Vengeance", 150, 125,2666, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Frequenza, url) values ("RAM", "G.SKILL", "Trident Z RGB", 250, 168, 3000, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Frequenza, url) values ("RAM", "Corsair", "Platinum Dominator", 100, 245, 3200, "Images/placeHolder");

#HDD
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, MBs, url) values ("HDD", "Western Digital", "HC550", 100, 80, 300, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, MBs, url) values ("HDD", "Segate", "Barracuda", 150, 400, 400, "Images/placeHolder");
#SSD
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, MBs, url) values ("SSD","Kingstone", "A400", 200, 300, 1300, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, MBs, url) values ("SSD","Kingstone", "KC600", 300, 270, 2000, "Images/placeHolder");

#GPU
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, VRAM, url) values ("GPU", "EVGA", "RTX3090TI", 2000, 100, 200, 1200, 6, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, VRAM, url) values ("GPU", "EVGA", "2060", 300, 20, 600, 1500, 8, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, VRAM, url) values ("GPU", "MSI", "RTX3070", 1700, 45, 600, 2000, 8, "Images/placeHolder");

#PSU #Si dice psu non apu
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_WATT, url) values ("PSU", "Seasonic", "FOCUS GX-650", 200, 100, 500, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_WATT, url) values ("PSU", "Corsair", "HX1200", 250, 200, 800, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_WATT, url) values ("PSU", "Asus", "ROG Strix", 400, 180, 1200, "Images/placeHolder");

#Case #matx=0, atx=1, eatx=2
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, FormaMobo, url) values ("CASE", "Corsair", "4000D", 200, 10, 1, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, FormaMobo, url) values ("CASE", "Corsair", "700D", 300, 20, 2, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, FormaMobo, url) values ("CASE", "Thermaltake", "Divider 200 TG Air Snow Micro", 100, 20, 0, "Images/placeHolder");

#Dissipatore
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, W_CPU, url) values ("DISSIPATORE", "Cooler Master", "MA612", 100, 100, 300, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, W_CPU, url) values ("DISSIPATORE", "Noctua", "NH-D12L", 400, 70, 700, "Images/placeHolder");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, W_CPU, url) values ("DISSIPATORE", "Deep Cool", "GAMMAXX GT BK", 35, 65, 150, "Images/placeHolder");

#Sconto
insert into Sconto values('2023-12-24', '2024-12-26', 40);

#Applicare (Sconto su che prodotti?)
insert into Applicare values('2023-12-24', '2024-12-26', 1);
insert into Applicare values('2023-12-24', '2024-12-26', 2);
insert into Applicare values('2023-12-24', '2024-12-26', 5);
insert into Applicare values('2023-12-24', '2024-12-26', 6);
insert into Applicare values('2023-12-24', '2024-12-26', 9);
insert into Applicare values('2023-12-24', '2024-12-26', 10);

#Comporre
insert into Comporre values (3, 1, 2);
insert into Comporre values (10, 1, 1);
insert into Comporre values (1, 1, 1);
insert into Comporre values (15, 1, 4);

insert into Comporre values (3, 2, 2);
insert into Comporre values (10, 2, 1);
insert into Comporre values (1, 2, 1);

insert into Comporre values (10, 3, 1);
insert into Comporre values (1, 3, 1);











