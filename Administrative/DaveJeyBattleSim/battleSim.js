class Unit{
    constructor(race, type, training, size,equipment){
        this.name = 0;
        this.attack = 0;
        this.defense = 0;
        this.power = 0;
        this.toughness = 0;
        this.morale = 0;
        this.health = 0;
        this.initHealth(size);
        this.initRace(race);
        this.initType(type);
        this.initTraining(training);
        this.initEquipment(equipment);
    }
    getName(){
        return this.name;
    }
    getAttack(){
        return this.attack;
    }
    getDefense(){
        return this.defense;
    }
    getPower(){
        return this.power;
    }
    getToughness(){
        return this.toughness;
    }
    getMorale(){
        return this.morale;
    }
    getHealth(){
        return this.health;
    }
    takeDamage(damage){
        this.health -= damage;
    }
    initType(type){
        switch(type){
        case "Levy":
            this.morale += -1;
            break;
        case "Infantry":
            this.defense += 1;
            this.toughness+= 1;
            break;
        case "Cavalry":
            this.attack += 1;
            this.power += 1;
            this.morale += 2;
            break;
        case "Archer":
            this.power += 1;
            this.power += 1;
            break;
        case "Flying":
            this.morale += 3;
            break;    
    }
}
initRace(race){
    switch(race){
        case "Bugbear":
            this.attack += 2;
            this.morale += 1;
            break;
        case "Dragonborn":
            this.attack += 2;
            this.power += 2;
            this.defense += 1;
            this.toughness += 1;
            this.morale += 1;
            break;
        case "Dwarf":
            this.attack += 3;
            this.power += 1;
            this.defense += 1;
            this.toughness += 2;
            this.morale += 1;
            break;
        case "Elf":
            this.attack += 2;
            this.morale += 1;
            break;
        case "Ghoul":
            this.attack += -1;
            this.defense += 2;
            this.toughness += 2;
            break;
        case "Gnoll":
            this.attack += 2;
            this.morale += 1;
            break;
        case "Gnome":
            this.attack += 1;
            this.power += -1;
            this.defense += 1;
            this.toughness += -1;
            this.morale += 1;
            break;
        case "Goblin":
            this.attack += -1;
            this.power += -1;
            this.defense += 1;
            this.toughness += -1;
            break;
        case "Hobgoblin":
            this.attack += 2;
            this.morale += 1;
            break;
        case "Human":
            this.attack += 2;
            this.morale += 1;
            break;
        case "Kobold":
            this.attack += -1;
            this.power += -1;
            this.defense += 1;
            this.toughness += -1;
            this.morale += -1;
            break;
        case "Lizardfolk":
            this.attack += 2;
            this.power += 1;
            this.defense += -1;
            this.toughness += 1;
            this.morale += 1;
            break;
        case "Ogre":
            this.power += 2;
            this.toughness += 2;
            this.morale += 1;
            break;
        case "Orc":
            this.attack += 2;
            this.power += 1;
            this.defense += 1;
            this.toughness += 1;
            this.morale += 2;
            break;
        case "Skeleton":
            this.attack += -2;
            this.power += -1;
            this.defense += 1;
            this.toughness += 1;
            this.morale += 1;
            break;
        case "Troll":
            this.power += 2;
            this.toughness += 2;
            break;
        case "Zombie":
            this.attack += -2;
            this.defense += 2;
            this.toughness += 2;
            this.morale += 2;
            break;
    }
}
    initHealth(size){
        switch(size){
            case "1d4":
                this.health = 4;
                break;
            case "1d6":
                this.health = 6;
                break;
            case "1d8":
                this.health = 8;
                break;
            case "1d10":
                this.health = 10;
                break;
            case "1d12":
                this.health = 12;
                break;    
        }
    }
    initEquipment(equipment){
        switch(equipment){
            case "Light":
                this.power += 1;
                this.defense += 1;
                break;
            case "Medium":
                this.power += 2;
                this.defense += 2;
                break;
            case "Heavy":
                this.power += 4;
                this.defense += 4;
                break;
            case "Super-Heavy":
                this.power += 8;
                this.defense += 8;
                break;    
        }

    }



    }





var results = "";

function offAttack(){
    var output = document.getElementById("output");
    initialize();
    console.log("Attack: " + off_atk);
    console.log("Defense: " + def_def);
    console.log("Power: " + off_pwr);
    console.log("Toughness: " + def_tgh);
    off_numAtk = parseInt(document.getElementById("off_numAtk").value);
    let c = 0;
    for(let i = 0; i < off_numAtk; i++){
        let isHit = hit(off_atk, def_def);
        if(isHit){
            c += (Math.max(1, (off_pwr - def_tgh)));
        }
    }
    def_health += (c * -1);
    results += "" + def_name + " takes " + c + " casualties. \n";
    output.innerText = results;
}

function defAttack(){
    var output = document.getElementById("output");
    initialize();
    console.log("Attack: " + def_atk);
    console.log("Defense: " + off_def);
    console.log("Power: " + def_pwr);
    console.log("Toughness: " + off_tgh);
    def_numAtk = parseInt(document.getElementById("def_numAtk").value);
    let c = 0;
    for(let i = 0; i < def_numAtk; i++){
        let isHit = hit(def_atk, off_def);
        if(isHit){
            c += (Math.max(1, (def_pwr - off_tgh)));
        }
    }
    off_health += (c * -1);
    results += "" + off_name + " takes " + c + " casualties. \n";
    output.innerText = results;    
}

function displayInfo(){

}

//Done
function initialize(){
    resetValues();
    initNames();
    initSize();
    initRace();
    initTraining();
    initEquip();
    initType();
    initSpec();
    initMods();
}
function resetValues(){
    off_name = "";
    off_numAtk = 1;
    off_atk = 0;
    off_def = 0;
    off_pwr = 0;
    off_tgh = 0;
    off_moral = 0;
    off_health = 0;

    def_name = "";
    def_numAtk = 1;
    def_atk = 0;
    def_def = 0;
    def_pwr = 0;
    def_tgh = 0;
    def_moral = 0;
    def_health = 0;
}
function initNames(){
    off_name = document.getElementById("off_name").value;
    def_name = document.getElementById("def_name").value;
}
function initSize(){
    //LOGIC ERROR IN INITIALIZING SIZE BEFORE EACH ATTACK
    var off_size = document.getElementById("off_size").value;
    switch(off_size){
        case "1d4":
            off_health = 4 * off_numAtk;
            break;
        case "1d6":
            off_health = 6 * off_numAtk;
            break;
        case "1d8":
            off_health = 8 * off_numAtk;
            break;
        case "1d10":
            off_health = 10 * off_numAtk;
            break;
        case "1d12":
            off_health = 12 * off_numAtk;
            break;    
    }
    var def_size = document.getElementById("def_size").value;
    switch(def_size){
        case "1d4":
            def_health = 4 * def_numAtk;
            break;
        case "1d6":
            def_health = 6 * def_numAtk;
            break;
        case "1d8":
            def_health = 8 * def_numAtk;
            break;
        case "1d10":
            def_health = 10 * def_numAtk;
            break;
        case "1d12":
            def_health = 12 * def_numAtk;
            break;    
    }
}
function initRace(){
    var off_race = document.getElementById("off_race").value;
    switch(off_race){
        case "Bugbear":
            off_atk += 2;
            off_moral += 1;
            break;
        case "Dragonborn":
            off_atk += 2;
            off_pwr += 2;
            off_def += 1;
            off_tgh += 1;
            off_moral += 1;
            break;
        case "Dwarf":
            off_atk += 3;
            off_pwr += 1;
            off_def += 1;
            off_tgh += 2;
            off_moral += 1;
            break;
        case "Elf":
            off_atk += 2;
            off_moral += 1;
            break;
        case "Ghoul":
            off_atk += -1;
            off_def += 2;
            off_tgh += 2;
            break;
        case "Gnoll":
            off_atk += 2;
            off_moral += 1;
            break;
        case "Gnome":
            off_atk += 1;
            off_pwr += -1;
            off_def += 1;
            off_tgh += -1;
            off_moral += 1;
            break;
        case "Goblin":
            off_atk += -1;
            off_pwr += -1;
            off_def += 1;
            off_tgh += -1;
            break;
        case "Hobgoblin":
            off_atk += 2;
            off_moral += 1;
            break;
        case "Human":
            off_atk += 2;
            off_moral += 1;
            break;
        case "Kobold":
            off_atk += -1;
            off_pwr += -1;
            off_def += 1;
            off_tgh += -1;
            off_moral += -1;
            break;
        case "Lizardfolk":
            off_atk += 2;
            off_pwr += 1;
            off_def += -1;
            off_tgh += 1;
            off_moral += 1;
            break;
        case "Ogre":
            off_pwr += 2;
            off_tgh += 2;
            off_moral += 1;
            break;
        case "Orc":
            off_atk += 2;
            off_pwr += 1;
            off_def += 1;
            off_tgh += 1;
            off_moral += 2;
            break;
        case "Skeleton":
            off_atk += -2;
            off_pwr += -1;
            off_def += 1;
            off_tgh += 1;
            off_moral += 1;
            break;
        case "Troll":
            off_pwr += 2;
            off_tgh += 2;
            break;
        case "Zombie":
            off_atk += -2;
            off_def += 2;
            off_tgh += 2;
            off_moral += 2;
            break;
    }
    var def_race = document.getElementById("def_race").value;
    switch(def_race){
        case "Bugbear":
            def_atk += 2;
            def_moral += 1;
            break;
        case "Dragonborn":
            def_atk += 2;
            def_pwr += 2;
            def_def += 1;
            def_tgh += 1;
            def_moral += 1;
            break;
        case "Dwarf":
            def_atk += 3;
            def_pwr += 1;
            def_def += 1;
            def_tgh += 2;
            def_moral += 1;
            break;
        case "Elf":
            def_atk += 2;
            def_moral += 1;
            break;
        case "Ghoul":
            def_atk += -1;
            def_def += 2;
            def_tgh += 2;
            break;
        case "Gnoll":
            def_atk += 2;
            def_moral += 1;
            break;
        case "Gnome":
            def_atk += 1;
            def_pwr += -1;
            def_def += 1;
            def_tgh += -1;
            def_moral += 1;
            break;
        case "Goblin":
            def_atk += -1;
            def_pwr += -1;
            def_def += 1;
            def_tgh += -1;
            break;
        case "Hobgoblin":
            def_atk += 2;
            def_moral += 1;
            break;
        case "Human":
            def_atk += 2;
            def_moral += 1;
            break;
        case "Kobold":
            def_atk += -1;
            def_pwr += -1;
            def_def += 1;
            def_tgh += -1;
            def_moral += -1;
            break;
        case "Lizardfolk":
            def_atk += 2;
            def_pwr += 1;
            def_def += -1;
            def_tgh += 1;
            def_moral += 1;
            break;
        case "Ogre":
            def_pwr += 2;
            def_tgh += 2;
            def_moral += 1;
            break;
        case "Orc":
            def_atk += 2;
            def_pwr += 1;
            def_def += 1;
            def_tgh += 1;
            def_moral += 2;
            break;
        case "Skeleton":
            def_atk += -2;
            def_pwr += -1;
            def_def += 1;
            def_tgh += 1;
            def_moral += 1;
            break;
        case "Troll":
            def_pwr += 2;
            def_tgh += 2;
            break;
        case "Zombie":
            def_atk += -2;
            def_def += 2;
            def_tgh += 2;
            def_moral += 2;
            break;
    }
}
function initTraining(){
    var off_training = document.getElementById("off_training").value;
    switch(off_training){
        case "Green":
            break;
        case "Regular":
            off_atk += 1;
            off_tgh += 1;
            off_moral += 1;
            break;
        case "Seasoned":
            off_atk += 1;
            off_tgh += 1;
            off_moral += 2;
            break;
        case "Veteran":
            off_atk += 1;
            off_tgh += 1;
            off_moral += 3;
            break;
        case "Elite":
            off_atk += 2;
            off_tgh += 2;
            off_moral += 4;
            break;
        case "Super-Elite":
            off_atk += 2;
            off_tgh += 2;
            off_moral += 5;
            break;
    }
    var def_training = document.getElementById("def_training").value;
    switch(def_training){
        case "Green":
            break;
        case "Regular":
            def_atk += 1;
            def_tgh += 1;
            def_moral += 1;
            break;
        case "Seasoned":
            def_atk += 1;
            def_tgh += 1;
            def_moral += 2;
            break;
        case "Veteran":
            def_atk += 1;
            def_tgh += 1;
            def_moral += 3;
            break;
        case "Elite":
            def_atk += 2;
            def_tgh += 2;
            def_moral += 4;
            break;
        case "Super-Elite":
            def_atk += 2;
            def_tgh += 2;
            def_moral += 5;
            break;
    }
}
function initEquip(){
    var off_equip = document.getElementById("off_equip").value;
    switch(off_equip){
        case "Light":
            off_pwr += 1;
            off_def += 1;
            break;
        case "Medium":
            off_pwr += 2;
            off_def += 2;
            break;
        case "Heavy":
            off_pwr += 4;
            off_def += 4;
            break;
        case "Super-Heavy":
            off_pwr += 8;
            off_def += 8;
            break;    
    }
    var def_equip = document.getElementById("def_equip").value;
    switch(def_equip){
        case "Light":
            def_pwr += 1;
            def_def += 1;
            break;
        case "Medium":
            def_pwr += 2; 
            def_def += 2;
            break;
        case "Heavy":
            def_pwr += 4;
            def_def += 4;
            break;
        case "Super-Heavy":
            def_pwr += 8;
            def_def += 8;
            break;    
    }
}
function initType(){
    var off_type = document.getElementById("off_type").value;
    switch(off_type){
        case "Levy":
            off_moral += -1;
            break;
        case "Infantry":
            off_def += 1;
            off_tgh += 1;
            break;
        case "Cavalry":
            off_atk += 1;
            off_pwr += 1;
            off_moral += 2;
            break;
        case "Archer":
            off_pwr += 1;
            off_moral += 1;
            break;
        case "Flying":
            off_moral += 3;
            break;    
    }
    var def_type = document.getElementById("def_type").value;
    switch(def_type){
        case "Levy":
            def_moral += -1;
            break;
        case "Infantry":
            def_def += 1;
            def_tgh += 1;
            break;
        case "Cavalry":
            def_atk += 1;
            def_pwr += 1;
            def_moral += 2;
            break;
        case "Archer":
            def_pwr += 1;
            def_moral += 1;
            break;
        case "Flying":
            def_moral += 3;
            break;    
    }
}
function initSpec(){
    var off_special = document.getElementById("off_special").value;
    switch(off_special){
        case "Nothing":
            break;
        case "Adamantine":
            break; 
        case "Dark Iron":
            off_pwr += 2;
            break;
        case "Minithril":
            break;
    }
    var def_special = document.getElementById("def_special").value;
    switch(def_special){
        case "Nothing":
            break;
        case "Adamantine":
            break;
        case "Dark Iron":
            def_pwr += 2;
            break;
        case "Minithril":
            break;
    }
}
function initMods(){
    off_atk_mod = document.getElementById("off_atk");
    off_def_mod = document.getElementById("off_def");
    off_pwr_mod = document.getElementById("off_pwr");
    off_tgh_mod = document.getElementById("off_tgh");

    def_atk_mod = document.getElementById("def_atk");
    def_def_mod = document.getElementById("def_def");
    def_pwr_mod = document.getElementById("def_pwr");
    def_tgh_mod = document.getElementById("def_tgh");

    if(off_atk_mod.value != "") off_atk += parseInt(off_atk_mod.value);
    if(off_def_mod.value != "") off_def += parseInt(off_def_mod.value);
    if(off_pwr_mod.value != "") off_pwr += parseInt(off_pwr_mod.value);
    if(off_tgh_mod.value != "") off_tgh += parseInt(off_tgh_mod.value);

    if(def_atk_mod.value != "") def_atk += parseInt(def_atk_mod.value);
    if(def_def_mod.value != "") def_def += parseInt(def_def_mod.value);
    if(def_pwr_mod.value != "") def_pwr += parseInt(def_pwr_mod.value);
    if(def_tgh_mod.value != "") def_tgh += parseInt(def_tgh_mod.value);
}

function clearLog(){
    results = "";
    output.innerText = "";
}




function hit(o_a, d_d){
    var r = roll();
    if((r + o_a) >= (10 + d_d)){
        console.log("roll = " + r + ". Hit.");
        return true;
    } 
    console.log("roll = " + r + ". Miss.")
    return false;
}

function roll(){
    var adv = document.getElementById("adv");
    if(adv.checked){
        return Math.max((Math.floor((Math.random() * 20)+1)),Math.floor(((Math.random() * 20)+1)));
    } return Math.floor(Math.random() * 20) + 1;
}

function displayError(error){
    output.innerText(error);
    return;
}

window.onload = function(){
    document.getElementById("off_button").onclick = offAttack;
    document.getElementById("def_button").onclick = defAttack;
    document.getElementById("clearlog").onclick = clearLog;
}