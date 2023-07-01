import { expect, test } from '@jest/globals'
import RestClient from '../infra/RestClient'

test('reset train', () => {
  const restClient = new RestClient()
  restClient.resetTrain('express_2000')
})