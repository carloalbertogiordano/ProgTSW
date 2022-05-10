USE Ecommerce;

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
insert into Pezzo (Id, tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core, url) values (1, "CPU", "Intel", "i7", 200, 300, 4.2, 8, "https://www.intel.com/content/dam/www/global/badges/11th-gen-core-i7-processors-badge-rwd.png.rendition.intel.web.64.64.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core, url) values ("CPU", "AMD", "Ryzen7", 150, 200, 3.2, 6, "https://drh1.img.digitalriver.com/DRHM/Storefront/Company/amd/images/product/thumbnail/930348-ryzen-5700g-100x100.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, N_Core, url) values ("CPU", "AMD", "Ryzen7", 250, 250, 4.4, 8, "https://drh1.img.digitalriver.com/DRHM/Storefront/Company/amd/images/product/thumbnail/930348-ryzen-5600g-100x100.png");

#Mobo #matx=0, atx=1, eatx=2
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "Asrock", "Z590 OC Formula", 300, 2, 6, 3, 1, "https://www.asrock.com/mb/photo/Z590%20OC%20Formula(M1).png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "MSI", "MPG Z690", 600, 8, 8, 5, 2, "https://asset.msi.com/resize/image/global/product/product_1635821283ae6029bbcbf048b318d8281c8f1511f8.png62405b38c58fe0f07fcef2367d8a9ba1/400.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_RAM, N_USB, N_PCI, formaMobo, url) values ("MOBO", "Asus", "ROG Crossair VIII Impact", 300, 2, 6, 3, 0, "https://dlcdnwebimgs.asus.com/gain/75CA7716-EAE6-4638-A66A-AA19057F5DF6/w717/h525/w292");

#RAM
insert into Pezzo (tipo, Marca, Modello, Prezzo, Frequenza, url) values ("RAM", "Corsair", "Vengeance", 150, 2666, "https://www.corsair.com/us/en/medias/sys_master/images/images/h72/h48/9838337654814/CMN32GX4M2Z3600C18W/Gallery/VENGEANCE_RGB_RT_WHITE_01/-CMN32GX4M2Z3600C18W-Gallery-VENGEANCE-RGB-RT-WHITE-01.png_515Wx515H");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Frequenza, url) values ("RAM", "G.SKILL", "Trident Z RGB", 250, 3000, "https://www.gskill.com/_upload/images/1809101101450.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Frequenza, url) values ("RAM", "Corsair", "Platinum Dominator", 100, 3200, "https://www.corsair.com/medias/sys_master/images/images/hce/h54/9255149633566/-CMT32GX4M2K4000C19-Gallery-DOMINATOR-PLAT-RGB-01.png");

#HDD
insert into Pezzo (tipo, Marca, Modello, Prezzo, MBs, url) values ("HDD", "Western Digital", "HC550", 100, 300, "https://www.westerndigital.com/content/dam/store/en-us/assets/products/internal-storage/ultrastar-dc-hc550-hdd/gallery/ultrastar-dc-hc550-hdd-16tb.png.wdthumb.319.319.webp");
insert into Pezzo (tipo, Marca, Modello, Prezzo, MBs, url) values ("HDD", "Segate", "Barracuda", 150, 400, "https://www.seagate.com/www-content/products/hard-drives/barracuda-hard-drive/_shared/images/barracuda-2-5-5tb-hero-left-400x400_l.png");
#SSD
insert into Pezzo (tipo, Marca, Modello, Prezzo, MBs, url) values ("SSD","Kingstone", "A400", 200, 1300, "https://media.kingston.com/kingston/product/ktc-product-ssd-a400-sa400s37-family-1-tn.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, MBs, url) values ("SSD","Kingstone", "KC600", 300, 2000, "https://media.kingston.com/kingston/product-card/ktc-product-ssd-kc600-skc600-1-tn.png");

#GPU
insert into Pezzo (tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM, url) values ("GPU", "EVGA", "RTX3090TI", 2000, 200, 1200, 6, "https://images.evga.com/products/gallery/png/24G-P5-4985-KR_LG_1.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM, url) values ("GPU", "EVGA", "2060", 300, 600, 1500, 8, "https://images.evga.com/products/gallery//png/06G-P4-2068-KR_LG_1.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, Wattaggio, Frequenza, VRAM, url) values ("GPU", "MSI", "RTX3070", 1700, 600, 2000, 8, "https://asset.msi.com/resize/image/global/product/product_160586327231263767b2bf0bf40643500523f6553d.png62405b38c58fe0f07fcef2367d8a9ba1/400.png");

#PSU #Si dice psu non apu
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_WATT, url) values ("PSU", "Seasonic", "FOCUS GX-650", 200, 500, "https://www.avtrend.it/wp-content/uploads/2020/10/Seasonic-FOCUS-GX-650-500x398.jpg");
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_WATT, url) values ("PSU", "Corsair", "HX1200", 250, 800, "https://www.avtrend.it/wp-content/uploads/2021/05/Corsair-HX1200.jpg");
insert into Pezzo (tipo, Marca, Modello, Prezzo, N_WATT, url) values ("PSU", "Asus", "ROG Strix", 400, 1200, "https://www.avtrend.it/wp-content/uploads/2021/05/Asus-ROG-Strix-750W.jpg");

#Case #matx=0, atx=1, eatx=2
insert into Pezzo (tipo, Marca, Modello, Prezzo, FormaMobo, url) values ("CASE", "Corsair", "4000D", 200, 1, "https://www.corsair.com/medias/sys_master/images/images/h44/h3b/9631023169566/base-4000d-airflow-config/Gallery/4000D_AF_BLACK_01/-base-4000d-airflow-config-Gallery-4000D-AF-BLACK-01.png_515Wx515H");
insert into Pezzo (tipo, Marca, Modello, Prezzo, FormaMobo, url) values ("CASE", "Corsair", "700D", 300, 2, "https://www.corsair.com/medias/sys_master/images/images/h3e/heb/8840365441054/-CC700D-Gallery-700d-001.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, FormaMobo, url) values ("CASE", "Thermaltake", "Divider 200 TG Air Snow Micro", 100, 0, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/0b6cd258d732892ac0f7248ef9ed4204/d/i/divider200tgair_w01.jpg");

#Dissipatore
insert into Pezzo (tipo, Marca, Modello, Prezzo, W_CPU, url) values ("DISSIPATORE", "Cooler Master", "MA612", 100, 300, "https://cdn.coolermaster.com/media/assets/1003/masterair-ma612-stealth-380x380-2-hover.png");
insert into Pezzo (tipo, Marca, Modello, Prezzo, W_CPU, url) values ("DISSIPATORE", "Noctua", "NH-D12L", 400, 700, "https://noctua.at/pub/media/catalog/product/cache/0cdbea399f8ed06da39b3854134f6934/n/h/nh_d12l_5.jpg");
insert into Pezzo (tipo, Marca, Modello, Prezzo, W_CPU, url) values ("DISSIPATORE", "Deep Cool", "GAMMAXX GT BK", 35, 150, "https://cdn.deepcool.com/public/ProductFile/DEEPCOOL/Cooling/CPUAirCoolers/GAMMAXX_GT_BK/Gallery/608X760/04.jpg?fm=webp&q=60");
