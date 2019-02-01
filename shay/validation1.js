function validate1() {
    var imageFirst = getImage(alphaNumCheck(document.forms["information"]["firstName"].value), "firstName");
    var imageLast = getImage(alphaNumCheck(document.forms["information"]["lastName"].value), "lastName");
    var imageGender = getImage(notSelectCheck(document.forms["information"]["gender"].value), "gender");
    var imageState = getImage(notSelectCheck(document.forms["information"]["state"].value), "state");

    document.getElementById("FirstName").appendChild(imageFirst);
    document.getElementById("LastName").appendChild(imageLast);
    document.getElementById("Gender").appendChild(imageGender);
    document.getElementById("State").appendChild(imageState);
}

// From validation2.js. takes in a boolean to check to see which image needs to be returned.
function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

// From validation2.js. Checks for characters that are not allowed.
function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) return true;
    else return false;

}

// Check to see if the user chose an option in a drop down.
function notSelectCheck(entry) {
    if (entry == "select") return false;
    else return true;
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}