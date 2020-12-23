Uporabni ukazi:

kubectl apply -f chat-deployment.yaml
kubectl logs -f chat-deployment-6679d6bb76-lhtgc
kubectl get pods

kubectl scale deployment chat-deployment --replicas=0
kubectl scale deployment chat-deployment --replicas=1
