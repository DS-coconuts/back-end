const solution = (answers) => {
    let answer = [];
    let len = [0, 0, 0];
    let student1 = [1, 2, 3, 4, 5];
    let student2 = [2, 1, 2, 3, 2, 4, 2, 5];
    let student3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];
    for (let i = 0; i < answers.length; i++) {
        if (student1[i % 5] === answers[i]) len[0]++;
        if (student2[i % 8] === answers[i]) len[1]++;
        if (student3[i % 10] === answers[i]) len[2]++;
    }
    for (let i = 0; i < len.length; i++) {
        if (len[i] === Math.max(...len)) answer.push(i + 1);
    }
    return answer;
};