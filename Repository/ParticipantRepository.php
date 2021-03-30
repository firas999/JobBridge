<?php

namespace App\Repository;

use App\Entity\Participant;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Participant|null find($id, $lockMode = null, $lockVersion = null)
 * @method Participant|null findOneBy(array $criteria, array $orderBy = null)
 * @method Participant[]    findAll()
 * @method Participant[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ParticipantRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Participant::class);
    }
    public function findByASC()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.Nom', 'ASC')
            ->getQuery()
            ->getResult()
            ;
    }
    public function countForStage(string $type)
    {
        return $this->createQueryBuilder('s')
            ->Where('s.typeParticipant = :type')
            ->setParameter('type', $type)
            ->select('count(s.id)')
            ->getQuery()
            ->getSingleScalarResult();
    }
    // /**
    //  * @return Participant[] Returns an array of Participant objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Participant
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */


  /*  public function countlikeNumber()
    {
        return $this->createQueryBuilder('r')
            ->select('count(r.id)')
            ->andWhere('r.idEvenement = :val')
            ->setParameter('val', 3)
            ->getQuery()
            ->getSingleScalarResult();
    }*/
}
