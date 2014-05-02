/* Battle Stats keeps track of all the clients and the hashtags along with counters*/
BattleStats =  new Meteor.Collection("battle");

if (Meteor.isClient) {
    Template.hello.greeting = function () {
	return "Welcome to the epic hashtagBattle.";
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
		
            }
        });

}