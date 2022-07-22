//funzione per controllare che i campi siano stati inseriti correttamente
function validateMarcaAndModello() {
    let marcaAndModello = /^[a-zA-Z0-9]{1,30}$/;
    if(!(marcaAndModello.test($("#marca").val()))){
        console.error("Err: Marca non valida");
    }
    if(!(marcaAndModello.test($("#modello").val()))){
        console.error("Err: Modello non valido");
    }
    return marcaAndModello.test($("#marca").val()) && marcaAndModello.test($("#modello").val());
}

function validatePrezzo() {
    let prezzo = /^(?!0\d)\d*(\.\d+)?$/;
    console.log($("#prezzo").val());
    if(!(prezzo.test($("#prezzo").val()))){
        console.error("Err: Prezzo non valida");
    }
    return prezzo.test($("#prezzo").val());
}

function validateQuantita() {
    let quant = /^[0-9]{0,3}$/;
    if(!(quant.test($("#quantita").val()))){
        console.error("Err: Quantita non valida");
    }
    return quant.test($("#quantita").val());
}

function validateDesc() {
    let desc = /^[a-zA-Z0-9].{1,10000}$/;
    if(!(desc.test($("#descrizione").val()))){
        console.error("Err: Descrizione non valida");
    }
    return desc.test($("#descrizione").val());
}

function validateCompulsoryProduct() {
    return (validateMarcaAndModello() && validatePrezzo() && validateQuantita() && validateDesc());
}

function validateByTipo() {
    let tipo = document.getElementById("tipo").value;
    switch (tipo) {
        case "CPU" :
            console.log("N_Core");
            const ncore = new RegExp('[1-9][0-9]{0,1}');
            let str = document.getElementById("n_core").value;
            return ncore.test(str);
            break;
        case "CASE" :
            console.log("case");
            let formaMobo = /^[0-2]$/;
            let num = /^[0-9]{1,2}$/;
            let strFormaMobo = document.getElementById("formaMobo").value;
            let strNram = document.getElementById("n_ram").value;
            let strNusb = document.getElementById("n_usb").value;
            let strNpci = document.getElementById("n_pci").value;
            return formaMobo.test(strFormaMobo) && num.test(strNram) && num.test(strNusb) && num.test(strNpci);
            break;
        case "DISSIPATORE" :
            console.log("diss");
            let wCpu = /[1-9][0-9]{0,3}/;
            let strwCpu = document.getElementById("w_cpu").value;
            return wCpu.test(strwCpu);
            break;
        case "GPU" :
            console.log("gpu");
            let wGpu = /[1-9][0-9]{0,3}/;
            let freqGpu = /^(?!0\d)\d*(\.\d+)?$/mg; //Frequenza espressa in GHz
            let vRam = /[1-9][0-9]{0,2}/;
            let strwGpu = document.getElementById("wattaggio").value;
            let strFreqGpu = document.getElementById("frequenza").value;
            let strvRam = document.getElementById("vram").value;
            return (wGpu.test(strwGpu) && freqGpu.test(strFreqGpu) && vRam.test(strvRam));
            break;
        case "MOBO" :
            console.log("mobo");
            let forma = /[0-2]/;
            let n = /[1-9][0-9]{0,2}/;
            let strForma = document.getElementById("formaMobo").value;
            let strnRam = document.getElementById("n_ram").value;
            let strnPci = document.getElementById("n_pci").value;
            let strnUsb = document.getElementById("n_usb").value;
            return (forma.test(strForma) && n.test(strnRam) && n.test(strnPci) && n.test(strnUsb));
            break;
        case "PSU" :
            let watt = /^[1-9](\d{1,4})?/;
            let strWatt = document.getElementById("wattaggio").value;
            return watt.test(strWatt);
            break;
        case "RAM" :
            console.log("ram");
            let frequenza = /^(?!0\d)\d*(\.\d+)?$/mg;
            let strFrequenza = document.getElementById("frequenza").value;
            return frequenza.test(strFrequenza);
            break;
        case "HDD":
        case "SSD":
            console.log("dati");
            let mbps = /\d{1,100}/;
            let strMbps = document.getElementById("mbs").value;
            return mbps.test(strMbps);
            break;
        default :
            return false;
    }


    return false;
}

function validateProductUpdate() {
    let tipo = document.getElementById("tipo").value;

    if(!(validateByTipo(tipo))){
        console.error("Err: Validazione su tipo non valid");
    }

    if(!(validateCompulsoryProduct()))
        console.error("ERR: Validazione prodotti obbligatori non valida");

    if (validateCompulsoryProduct() && validateByTipo())
        return true;
    return false;
}

function disableAll() {
    document.getElementById("marca").disabled = true;
    document.getElementById("modello").disabled = true;
    document.getElementById("prezzo").disabled = true;
    document.getElementById("quantita").disabled = true;
    document.getElementById("wattaggio").disabled = true;
    document.getElementById("frequenza").disabled = true;
    document.getElementById("n_core").disabled = true;
    document.getElementById("n_ram").disabled = true;
    document.getElementById("n_usb").disabled = true;
    document.getElementById("n_pci").disabled = true;
    document.getElementById("mbs").disabled = true;
    document.getElementById("vram").disabled = true;
    document.getElementById("w_cpu").disabled = true;
    document.getElementById("formaMobo").disabled = true;
    document.getElementById("descrizione").disabled = true;
}

//funzione per l'aggionamento della quantità nel DB
function aggiorna(){
    document.getElementById("stampa").style.display="none";
    document.getElementById("frameCatalogo").style.display="block";
    document.getElementById("frameCatalogo").src="Catalogo.jsp";
}

function resetForm(){
    document.getElementById("wattaggio").disabled = false;
    document.getElementById("frequenza").disabled = false;
    document.getElementById("n_core").disabled = false;
    document.getElementById("n_ram").disabled = false;
    document.getElementById("n_usb").disabled = false;
    document.getElementById("n_pci").disabled = false;
    document.getElementById("mbs").disabled = false;
    document.getElementById("vram").disabled = false;
    document.getElementById("w_cpu").disabled = false;
    document.getElementById("formaMobo").disabled = false;
}

function enableDefault(){
    document.getElementById("marca").disabled = false;
    document.getElementById("modello").disabled = false;
    document.getElementById("prezzo").disabled = false;
    document.getElementById("quantita").disabled = false;
    document.getElementById("image").disabled = false;
    document.getElementById("descrizione").disabled = false;
}

//prende il tipo di prodotto scelto e disabilita i campi non necessari
function controllo() {
    let tipo = document.getElementById("tipo").value;
    let wattaggio, frequenza, core, ram, usb, pci, vram, wcpu, forma, mbs;
    console.log(tipo);
    resetForm();
    enableDefault();
    switch (tipo) {
        case "HDD":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            break;

        case "SSD":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            break;

        case "CASE":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "CPU":
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "DISSIPATORE":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "GPU":
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "MOBO":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "PSU":
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "RAM":
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            break;

        default:
            disableAll();
            break;

    }
}

//Funzione che stampa il form per l'inserimento di un nuovo prodotto nel database
function nuovo() {
    document.getElementById("frameCatalogo").style.display="none";
    document.getElementById("stampa").style.display="block";
    document.getElementById("stampa").innerHTML =
        "<form action=\"Upload\" method=\"post\" onsubmit=\"return validateProductUpdate()\" enctype=\"multipart/form-data\" >" +
        "<table>" +
        //La funzione controllo() è chiamata per disabilitare i campi del form in base al tipo di prodotto scelto
        "<select name=\"tipo\" id=\"tipo\">" +
        "<option value=\"none\" selected disabled hidden>Selezione un opzione</option>"+
        "<option onclick=\"controllo()\" value=\"HDD\">Hdd</option>" +
        "<option onclick=\"controllo()\" value=\"SSD\">Ssd</option>" +
        "<option onclick=\"controllo()\" value=\"CASE\">Case</option>" +
        "<option onclick=\"controllo()\" value=\"CPU\">Cpu</option>" +
        "<option onclick=\"controllo()\" value=\"DISSIPATORE\">Dissipatore</option>" +
        "<option onclick=\"controllo()\" value=\"GPU\">Gpu</option>" +
        "<option onclick=\"controllo()\" value=\"MOBO\">Mobo</option>" +
        "<option onclick=\"controllo()\" value=\"PSU\">Psu</option>" +
        "<option onclick=\"controllo()\" value=\"RAM\">Ram</option>" +
        "</select>" +
        "<tr>" +
        "<td>Marca</td>" +
        "<td><input type=\"text\" name=\"marca\" id=\"marca\" required disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Modello</td>" +
        "<td><input type=\"text\" name=\"modello\" id=\"modello\" required disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Prezzo</td>" +
        "<td><input type=\"text\" name=\"prezzo\" id=\"prezzo\" required disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Quantità</td>" +
        "<td><input type=\"text\" name=\"quantita\" id=\"quantita\" required disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Wattaggio</td>" +
        "<td><input type=\"text\" name=\"wattaggio\" id=\"wattaggio\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Frequenza</td>" +
        "<td><input type=\"text\" name=\"frequenza\" id=\"frequenza\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di cores</td>" +
        "<td><input type=\"text\" name=\"N_Core\" id=\"n_core\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di slot RAM</td>" +
        "<td><input type=\"text\" name=\"N_Ram\" id=\"n_ram\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di USB</td>" +
        "<td><input type=\"text\" name=\"N_Usb\" id=\"n_usb\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero slot PCI</td>" +
        "<td><input type=\"text\" name=\"N_Pci\" id=\"n_pci\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>MB/s</td>" +
        "<td><input type=\"text\" name=\"MBs\" id=\"mbs\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Quantià VRAM</td>" +
        "<td><input type=\"text\" name=\"Vram\" id=\"vram\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Watt possibili dissipare</td>" +
        "<td><input type=\"text\" name=\"W_Cpu\" id=\"w_cpu\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Forma MOBO</td>" +
        "<td><input type=\"text\" name=\"formaMobo\" id=\"formaMobo\" disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Immagine</td>" +
        "<td><input type=\"file\" name=\"image\" id=\"image\" accept='.png, .jpeg, .jpg, .svg' required disabled/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Descrizione</td>" +
        "<td><input type=\"text\" name=\"descrizione\" id=\"descrizione\" required disabled/></td>" +
        "</tr>" +
        "</table>" +
        "<input type=\"submit\" value=\"Invia\"/>" +
        "</form>";

}