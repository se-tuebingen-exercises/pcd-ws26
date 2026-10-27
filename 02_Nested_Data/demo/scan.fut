
def prefix_sum [n] (xs: [n]f64) : [n]f64 =
  scan (+) 0 xs

-- def nesting_depth [n] (chars: [n]u8): [n]i32 =
--   scan (+) 0 (map (\c -> if c == '(' then 1 else if c == ')' then -1 else 0) chars)

entry main (xs: [4]f64) : [4]f64 =
  prefix_sum xs

