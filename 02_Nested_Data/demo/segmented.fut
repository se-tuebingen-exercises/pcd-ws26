
def segmented_scan_add [k] (flags: [k]bool) (values: [k]i64): [k]i64 =
  let combine (b1, v1) (b2, v2) = (b1 || b2, if b2 then v2 else v1 + v2)
  let pairs = scan combine (false, 0) (zip flags values)
  in map (.1) pairs

def main : [6]i64 =
  let flags  = [true, false, false, true, false, false]
  let values = [1, 2, 3, 4, 5, 6]
  in segmented_scan_add flags values

