/* Battle Stats keeps track of all the clients and the hashtags along with counters*/
BattleStats =  new Meteor.Collection("battle");

if (Meteor.isClient) {
    Template.hello.greeting = function () {
	return "Welcome to the epic hashtagBattle. Remember the scores hold valid for 2 minutes.";
    };
    //Declarations
    var clientId = Meteor.uuid();
    var brand1;
    var brand2;

    //Make the data available to the Template battle_results
    Template.battle_results.battle = function(){
	return BattleStats.find({_id: clientId});
    }

    //Handle Events
    Template.form.events({
	    'click #button': function () {
		brand1 = document.getElementById("brand1").value;
		brand2 = document.getElementById("brand2").value;
		// MongoDB Insert
		//Check if it's a new request from the same client
		if(BattleStats.find({_id:clientId}))
		    BattleStats.remove({_id: clientId});
		//New client
		BattleStats.insert({_id: clientId, data: [{brand: brand1, score: 0}, {brand: brand2, score: 0}]});
		// Pass the input data to the server.
		Meteor.call('startStream', clientId, brand1, brand2, function(err, response){
			if (err) 
			    alert("error: "+ err)
				});
	    }});
    //Empty the database
    Template.form.events({
            'click #buttonStop': function () {
                BattleStats.remove({_id: clientId});
		Meteor.call('stopStream', function(err, response){
                            alert("Error: stopStream is yet to be hooked up to this button :("+ err)
                                });
            }
        });
    
}