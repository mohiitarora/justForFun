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
	    consumer_key: 'o5UKhsFYP2VluHFKoDyRDdhbN',
	    consumer_secret: 'pdPugTUALH1divj5HWaOF5WEwdz05BDnbHsY6pflnNSoRCbEDG',
	    access_token_key: '98678259-fxMNnOJZlqL5FwHCUA43ZTbTTxBOF4Uabk2bIEqMk',
	    access_token_secret: 'FJNMiaOpS8Ledr7KHkObPEiIG9It98CZlJtUjLUB59tCm'
	});

    Meteor.methods({
	    startStream: function (clientId, brand1, brand2) {
		//Prepare string for Stream Search
		var searchString = brand1.concat(",", brand2);
		//console.log("got here");
		twit.stream('statuses/filter', {
			'track': searchString
			    }, function(stream) {
			
			stream.on('data', function(data) {
				Fiber( function() {

					var tweetText = data.text;
					//identify hashTag and increment corresponding counter
					var ht1Present = tweetText.search(brand1)>0;
					var ht2Present = tweetText.search(brand2)>0;

					if (ht1Present && ht2Present){
					    BattleStats.update({_id: clientId},{$inc: {"data.1.score": 1}});
					    BattleStats.update({_id: clientId},{$inc: {"data.0.score": 1}});
					}else if(ht1Present)
					    BattleStats.update({_id: clientId},{$inc: {"data.0.score": 1}});
					else
					    BattleStats.update({_id: clientId},{$inc: {"data.1.score": 1}});

					//console.log(data.text);
					
					  }).run();
			    });
		    });
		return "clientId";
	    }});
}