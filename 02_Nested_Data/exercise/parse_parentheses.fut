
def nesting_depth [n] (characters: [n]u8): [n]i64 =
  assert false (replicate n 0) -- TODO

def parse_parentheses [n] (characters: [n]u8) : bool =
  assert false false -- TODO

let good1 = "()()"
let good2 = "((a))"
let bad1  = "())"
let bad2  = ")("

entry main : [4]bool = [
  parse_parentheses good1,
  parse_parentheses good2,
  parse_parentheses bad1,
  parse_parentheses bad2]

