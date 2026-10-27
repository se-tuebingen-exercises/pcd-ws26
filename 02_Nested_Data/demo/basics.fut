def add [n] (a: [n]f64) (b: [n]f64) : [n]f64 =
  map2 (+) a b

def scale [n] (a: [n]f64) : [n]f64 =
  map (*2) a

def factorial (n: i64): i64 =
  reduce (*) 1 (1...n)

entry main [n] (a: [n]f64) (b: [n]f64) : ([n]f64, [n]f64, i64) =
  (scale a, add a b, factorial 10)

