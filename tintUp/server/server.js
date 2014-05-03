//Static Space
var Twitter = Meteor.require("twitter");
var Fiber = Npm.require('fibers');
BattleStats = new Meteor.Collection("battle");

if (Meteor.isServer) {
    Meteor.startup(function () {
	    BattleStats.remove({});	                                                              
	});
    //App details
        var twit = new Twitter({
		consumer_key: 'WFEXEONzvGOXNUEgRNvLsd0ly',
	    consumer_secret: 'nzdYATfDAqxM3SMxhe69Ov2i3dHRp1SIVtUDAnkkXZWvWGZPs5',
	    access_token_key: '98678259-fxMNnOJZlqL5FwHCUA43ZTbTTxBOF4Uabk2bIEqMk',
	    access_token_secret: 'FJNMiaOpS8Ledr7KHkObPEiIG9It98CZlJtUjLUB59tCm'
	});
    /*    var twit = new Twitter({                                                                                                          
            consumer_key: 'z3kvW9SblFaDK5nQvnw0pw',
            consumer_secret: '8DLkKZsiHzdiulQvVhoSbe1yj77zO2uQ7kGL7MJsDk4',
            access_token_key: '98678259-y1LVfhSv6K5nJ4VmqTiJwI0v9WcwVARPZaVXQ2wp0',
            access_token_secret: '301LhzHyEvgCP7JANbQKfxEe9o7p9HusH9JgNgxvqJU'
	    });   
	*/
    Meteor.methods({
	    stopStream: function() {
		//do nothing
		var t = '1';
	    },
	    startStream: function (clientId, brand1, brand2) {
		//Prepare string for Stream Search
		var searchString = brand1.concat(",", brand2);
		//console.log("got here");
		twit.stream('statuses/filter', {'track': searchString}, function(stream) {

			stream.on('data', function(data) {
				Fiber( function() {
					var tweetText = data.text;
					tweetText.toLowerCase();
					brand1.toLowerCase();
					brand2.toLowerCase();
					//identify hashTag and increment corresponding counter
					var ht1Present = tweetText.search(brand1);
					var ht2Present = tweetText.search(brand2);
					
					if(ht1Present != -1)
					    BattleStats.update({_id: clientId},{$inc: {"data.0.score": 1}});
					if(ht2Present != -1)
					    BattleStats.update({_id: clientId},{$inc: {"data.1.score": 1}});
					
					//console.log(data.text);
				    }).run();
			    });
			//stream.on('disconnect', function (disconnectMessage) {
			//stream.destroy();
				//console.log('disconnect', disconnectMessage)
				//    });
			/*stream.on('reconnect', function (req, res, ival) {
				console.log('reconnect', ival)
				    });
			stream.on('connect', function (req) {
				console.log('connect')
				});*/
			//console.log("destroying stream");
		    });
		return "clientId";
	    }});
}
