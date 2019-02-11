function validate1() {
    var first = alphaCheck(document.forms["information"]["firstName"].value);
    var imageFirst = getImage(first, "firstName");
    var last = alphaCheck(document.forms["information"]["lastName"].value);
    var imageLast = getImage(last, "lastName");
    var gender = notSelectCheck(document.forms["information"]["gender"].value);
    var imageGender = getImage(gender, "gender");
    var state = notSelectCheck(document.forms["information"]["state"].value);
    var imageState = getImage(state, "state");

    document.getElementById("FirstName").appendChild(imageFirst);
    document.getElementById("LastName").appendChild(imageLast);
    document.getElementById("Gender").appendChild(imageGender);
    document.getElementById("State").appendChild(imageState);

    var url = window.location.href; // Gets current url.
    var temp = url.split(""); // Split url into an array.

    var i;
    for (i = url.length - 4; i >= 0; i--) { // Loops through the array until it reaches '/'.
        if (temp[i] == '/') break;
    }

    url = url.slice(0, i); // Slices url at the last '/'
    url += "/validation2.html"; // Adds the url of the validation2.html page.

    if (first && last && gender && state) window.location = url; // Redirects to validation2.html if all of the inputs are correct.
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

// Checks to make sure there are only letters.
function alphaCheck(entry) {
    let regex = /^[a-z]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

// Check to see if the user chose an option in a drop down.
function notSelectCheck(entry) {
    if (entry == "select") return false;
    else return true;
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}