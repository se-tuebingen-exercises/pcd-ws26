import torch
import math

H = 4
N = 8
M = 16
D = 12

def attention(input, query_weights, key_weights, value_weights):
    input = input.unsqueeze(0)
    query = input @ query_weights
    key = input @ key_weights
    value = input @ value_weights
    scores = (query @ key.transpose(1, 2)) / math.sqrt(D)
    weights = torch.softmax(scores, dim=-1)
    output = weights @ value
    return output

input = torch.randn(N, M)
query_weights = torch.randn(H, M, D)
key_weights = torch.randn(H, M, D)
value_weights = torch.randn(H, M, D)

output = attention(input, query_weights, key_weights, value_weights)

print("Output shape:", output.shape)
print("Output:")
print(output)
