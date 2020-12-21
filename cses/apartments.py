n, m, k = [int(_) for _ in input().split()]
desired_sizes = [int(_) for _ in input().split()]
apartments = [int(_) for _ in input().split()]
desired_sizes.sort()
apartments.sort()
ap_index = 0
d_index = 0
number_of_good_apartments = 0
while ap_index < len(apartments) and d_index < len(desired_sizes):
    if apartments[ap_index] < desired_sizes[d_index] - k:
        # check the next apartment, the size of this one (ap_index) doesn't suit anybody
        ap_index += 1
    elif apartments[ap_index] >= desired_sizes[d_index] - k and apartments[ap_index] <= desired_sizes[d_index] + k:
        number_of_good_apartments += 1
        ap_index += 1
        d_index += 1
    else:
        # we can't satisfy this (d_index) applicant, move to the next
        d_index += 1
print(number_of_good_apartments)