import torch

def nbody_step(positions, velocities):
    ...

positions = torch.tensor([
    [0.0, 0.0],
    [1.0, 0.0],
    [0.0, 1.0]
])

velocities = torch.tensor([
    [0.0, 0.1],
    [0.0, 0.0],
    [0.1, 0.0]
])

new_pos, new_vel = nbody_step(positions, velocities)

print("Positions:")
print(new_pos)
print("Velocities:")
print(new_vel)

