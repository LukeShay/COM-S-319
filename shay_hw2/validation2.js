function validate2() {
    var emailImage = getImage(emailCheck(document.forms["contact information"]["email"].value), "email");
    document.getElementById("Email").appendChild(emailImage);

    var phoneImage = getImage(phoneCheck(document.forms["contact information"]["number"].value), "phone");
    document.getElementById("Phone").appendChild(phoneImage);

    var addressImage = getImage(addressCheck(document.forms["contact information"]["address"].value), "address");
    document.getElementById("Address").appendChild(addressImage);

}

// From example.
function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

// From example.
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

// From example.
function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

// Checks to see if input var only contains letters.
function alphaCheck(entry) {
    let regex = /^[a-z]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

// Checks to see if input var only contains numbers.
function numCheck(entry) {
    let regex = /^[0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

// Validates the phone number.
function phoneCheck(phone) {
    var list, i;

    if (phone.length == 10) return numCheck(phone);
    else if (phone.length == 12) list = phone.split('-');
    else return false;

    if (list.length == 3 && list[0].length == 3 && list[1].length == 3 && list[2].length == 4) {
        for (i = 0; i < 3; i++) {
            if (!numCheck(list[i])) return false;
        }
        return true;
    } else return false;
}

// Validates the address.
function addressCheck(address) {
    if (address == null) return false;
    var list;

    list = address.split(',');
    if (list.length == 2 && alphaCheck(list[0].trim()) && alphaCheck(list[1].trim())) return true;
    else return false;
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}