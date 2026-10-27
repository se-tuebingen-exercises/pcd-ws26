
def multiply_ell [l] [n] [m] (columns: [n][l]i64) (values: [n][l]f64) (vector: [m]f64) : [n]f64 =
  map2 (\cs vs -> reduce (+) 0.0 (map2 (\c v -> v * vector[c]) cs vs)) columns values

def segmented_scan_add [k] (flags: [k]bool) (values: [k]i64): [k]i64 =
  let combine (b1, v1) (b2, v2) = (b1 || b2, if b2 then v2 else v1 + v2)
  let pairs = scan combine (false, 0) (zip flags values)
  in map (.1) pairs

def from_coo [k] [n] (rows: [k]i64) (columns: [k]i64) (values: [k]f64) (m : i64) (l : i64) : ([n][l]i64,[n][l]f64) =
  let counts = reduce_by_index (replicate m 0) (+) 0 rows (replicate k 1)
  let offsets = scan (+) 0 counts
  let flags = reduce_by_index (replicate k 0) (+) 0 offsets (replicate m 1)
  let indices = segmented_scan_add (map (>0) flags) (replicate k 1)
  let positions = zip rows (map (\i -> i - 1) indices)
  let ell_columns = scatter_2d (replicate n (replicate l 0)) positions columns
  let ell_values = scatter_2d (replicate n (replicate l 0.0)) positions values
  in (ell_columns, ell_values)

def into_coo [n] [l] (columns: [n][l]i64) (values: [n][l]f64) (k : i64) : ([k]i64, [k]i64, [k]f64) =
  let flags = map (\x -> if x == 0.0 then 0 else 1) (flatten values)
  let counts = map (\vs -> reduce (+) 0 (map (\x -> if x == 0.0 then 0 else 1) vs)) values
  let positions = map (\i -> i - 1) (scan (+) 0 flags)
  let masked_values = map2 (\f v -> if f == 0 then 0.0 else v) flags (flatten values)
  let masked_columns = map2 (\f c -> if f == 0 then 0 else c) flags (flatten columns)
  let coo_rows = map (\i -> i - 1) (scan (+) 0 (reduce_by_index (replicate k 0) (+) 0 counts (replicate n 1)))
  let coo_values = reduce_by_index (replicate k 0) (+) 0 positions masked_values
  let coo_columns = reduce_by_index (replicate k 0) (+) 0 positions masked_columns
  in (coo_rows, coo_columns, coo_values)

def main : []f64 =
  let columns = [[0, 2],[1, 0],[0, 2],[3, 0]]
  let values  = [[1.0, 2.0],[3.0, 0.0],[4.0, 5.0],[6.0, 0.0]]
  let vector = [1.0, 2.0, 3.0, 4.0]

  in multiply_ell columns values vector

-- def main : ([4][4]i64,[4][4]f64) =
--   let rows    = [0, 0, 1, 2, 2, 3]
--   let columns = [0, 2, 1, 0, 2, 3]
--   let values  = [1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
--   in from_coo rows columns values 4 4

-- def main : ([6]i64,[6]i64,[6]f64) =
--   let columns = [[0, 2, 0], [1, 0, 0], [0, 0, 0], [0, 2, 3]]
--   let values  = [[1.0, 2.0, 0.0], [3.0, 0.0, 0.0], [0.0, 0.0, 0.0], [4.0, 5.0, 6.0]]
--   in into_coo columns values 6


