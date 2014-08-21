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
		consumer_key: '',
		consumer_secret: '',
		access_token_key: '',
	    access_token_secret: ''
	    });

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
					tweetText = tweetText.toLowerCase();
					brand1 = brand1.toLowerCase();
					brand2 = brand2.toLowerCase();
					//identify hashTag and increment corresponding counter
					var ht1Present = tweetText.search(brand1);
					var ht2Present = tweetText.search(brand2);
					
					if(ht1Present != -1)
					    BattleStats.update({_id: clientId},{$inc: {"data.0.score": 1}});
					if(ht2Present != -1)
					    BattleStats.update({_id: clientId},{$inc: {"data.1.score": 1}});
					
					console.log(data.text);
				    }).run();
				if(stream){
				    setTimeout(stream.destroy, 120000);
				}
			    });
		    });
		return "clientId";
	    }});
}
