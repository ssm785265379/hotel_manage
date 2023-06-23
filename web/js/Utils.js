function checkIdentity(identity){
    var reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
    if(reg.test(identity)){
        return true;
    }else{
        return false;
    }
}
function checkPhone(phone){
    var reg = /^1[3-9][0-9]{9}$/;
    if(reg.test(phone)){
        return true;
    }else{
        return false;
    }
}
