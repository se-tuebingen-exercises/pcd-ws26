
def radix_split [n] (bits: [n]bool) (values: [n]i64) : [n]i64 =
  assert false (replicate n 0) -- TODO

def bit_mask [n] (bit_index: i64) (values: [n]i64) : [n]bool =
  assert false (replicate n false) -- TODO

def sort_bytes [n] (values: [n]i64) : [n]i64 =
  assert false (replicate n 0) -- TODO

entry main : [4]i64 =
  sort_bytes [170, 85, 255, 0]

