/*global cordova, module*/

module.exports = {
    recordEvent: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "MomentsPlugin", "recordEvent", [name]);
    }
};
