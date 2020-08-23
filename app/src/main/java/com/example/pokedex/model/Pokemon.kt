package com.example.pokedex.model

class Pokemon {
    /*
    public int id { get; set; }
        public string num { get; set; }
        public string name { get; set; }
        public string img { get; set; }
        public List<string> type { get; set; }
        public string height { get; set; }
        public string weight { get; set; }
        public string candy { get; set; }
        public int candy_count { get; set; }
        public string egg { get; set; }
        public double spawn_chance { get; set; }
        public double avg_spawns { get; set; }
        public string spawn_time { get; set; }
        public List<double> multipliers { get; set; }
        public List<string> weaknesses { get; set; }
        public List<Evolution> next_evolution { get; set; }
        public List<Evolution> prev_evolution { get; set; }
    */
    var id:Int = 0
    var num:String? = null
    var name:String? = null
    var img:String? = null
    var type:List<String>? = null
    var height:String? = null
    var weight:String? = null
    var candy:String? = null
    var candyCount:Int = 0
    var egg:String? = null
    var spawnChance:Double=0.toDouble()
    var avgSpawns:Double=0.toDouble()
    var spawnTime:String?=null
    var multipliers:List<Double>?=null
    var weaknesses:List<String>?=null
    var next_evolution:List<Evolution>?=null
    var prev_evolution:List<Evolution>?=null
}