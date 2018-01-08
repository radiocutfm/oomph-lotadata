# Cordova LotaData Plugin for OOMPH

Simple plugin to get and populate location or events within LotaData SDK

## Using

Install the plugin

    $ cordova plugin add https://github.com/adieunix/oomph-lotadata.git
    
Use the plugin 

    lotadata.recordEvent('eventName', function(m) {
        // SUCCESS HANDLER
    }, function(e) {
        // ERROR HANDLER
    });