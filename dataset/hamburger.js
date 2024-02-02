const solution = (ingredient) => {
    let answer = 0;
    let stack = []
    ingredient.forEach(v => {
        stack.push(v)
        if (stack.length >= 4) {
            if (stack.slice(-4).join('') === '1231') {
                for (let i = 0; i < 4; i++) {
                stack.pop()
            }
            answer++
            }
        }
    })
    return answer;
}