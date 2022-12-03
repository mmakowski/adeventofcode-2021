use std::borrow::Borrow;

enum Num {
    Pair(Num, Num),
    Raw(u32),
    // hack
    Empty
}

fn read_input(inp: &str) -> &Vec<Num> {
    &Vec::new()
}

fn add(nums: &Vec<Num>) -> Num {
    let mut curr = Num::Empty;
    for num in nums {
        curr = Num::Pair(curr, *num)
    }

    Num::Raw(42)
}



#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example_1() {
        let input = r"[1,1]
[2,2]
[3,3]
[4,4]";
        let expected_sum = r"[[[[3,0],[5,3]],[4,4]],[5,5]]";
        add(read_input(input));
        assert_eq!(result, 4);
    }
}
