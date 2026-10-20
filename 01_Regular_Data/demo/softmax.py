import time
import torch

def softmax(x: torch.Tensor) -> torch.Tensor:
    y = torch.exp(x - x.max())
    return y / y.sum()

# def softmax(x: torch.Tensor) -> torch.Tensor:
#     a = x.max()             # []
#     b = x - a               # [n]
#     c = torch.exp(b)        # [n]
#     d = c.sum()             # []
#     y = c / d               # [n]
#     return y

# def softmax(x: torch.Tensor) -> torch.Tensor:
#     a = x.amax(dim=1, keepdim=True)     # [m, 1]
#     b = x - a                           # [m, n]
#     c = torch.exp(b)                    # [m, n]
#     d = c.sum(dim=1, keepdim=True)      # [m, 1]
#     y = c / d                           # [m, n]
#     return y


v = torch.randn(4)
start = time.time()

for _ in range(10):
    w = softmax(v)

elapsed = time.time() - start
print(f"{elapsed:.6f}")
print(w[0], w[1], w[2], w[3])

