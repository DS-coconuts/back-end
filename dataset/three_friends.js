const solution = (numbers) => {
    let answer = 0
    let n = numbers.length;
	
    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            for (let k = j + 1; k < n; k++) {
                if (numbers[i] + numbers[j] + numbers[k] === 0) answer++
            }
        }
    }
    return answer;
}