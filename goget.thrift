namespace java com.alexgilleran.goget.thrift

enum Sort {
	CO2, FUEL, PERCENTAGE;
}

struct ThriftPod {
	1: i32 id,
	2: i32 placeInOrder,
	3: i32 tripCount,
	4: i32 saveableTripCount,
	5: double saveableCo2,
	6: double saveableTripFraction,
	7: double saveableFuel,
	8: string name,
	9: double latitude,
	10: double longitude
}
    
service PodService {
   list<ThriftPod> getPods(1: i32 maxTrip, 2: i32 from, 3: i32 to, 4: Sort sort)
   ThriftPod getPod(1: i32 id, 2: i32 maxTrip)
}