import { expect, test } from '@jest/globals'
import { makeEmptyTrain } from './helpers'

test('empty train has some coaches', () => {
  const train = makeEmptyTrain()
  const coaches = train.getCoaches()
  expect(coaches).toEqual(["A", "B", "C", "D", "E"])
})