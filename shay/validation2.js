function validate2() {
    var emailImage = getImage(emailCheck(document.forms["contact information"]["email"].value), "email");
    document.getElementById("Email").appendChild(emailImage);
    var phoneImage = getImage(phoneCheck(document.forms["contact information"]["number"].value), "phone");
    document.getElementById("Phone").appendChild(phoneImage);

}

function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

function emailCheck(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0] + periodSplit[1])) {
            return true;
        }
    }
    return false;
}


function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function phoneCheck(phone) {
    let regex = /^[0-9]+$/i;
    var list;
    var str;
    if (phone.length > 13) return false;
    else if (phone.length == 10 && phone.match(regex)) return true;
    else if (phone.length == 12) list = phone.split("-");
    else return false;

    if (list.length == 3 && list[0].length == 3 && list[1].length == 3 && list[2].length == 4) {
        for (str in list) {
            if (!str.match(regex)) return false;
        }
        return true;
    } else return false;

}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}